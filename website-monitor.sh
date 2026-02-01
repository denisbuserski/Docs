#!/usr/bin/env bash
set -euo pipefail

WEBSITES_FILE="${WEBSITES_FILE:-websites.txt}"
CHECK_INTERVAL="${CHECK_INTERVAL:-5}"   # seconds between HTTP checks
HEADER_INTERVAL="${HEADER_INTERVAL:-1}" # seconds between clock updates
TIMEOUT="${TIMEOUT:-8}"                 # curl max time per request
UA="${UA:-webmon/1.0}"

# Temporary files for sharing data between background processes
IP_FILE="/tmp/webmon_ip_$$"
LOC_FILE="/tmp/webmon_loc_$$"

if [[ ! -f "$WEBSITES_FILE" ]]; then
  echo "Error: $WEBSITES_FILE not found"
  exit 1
fi

# Read websites (skip blank lines and comments)
mapfile -t URLS < <(grep -vE '^\s*(#|$)' "$WEBSITES_FILE" || true)
N="${#URLS[@]}"
if (( N == 0 )); then
  echo "Error: no URLs found in $WEBSITES_FILE"
  exit 1
fi

# State arrays
declare -a STATE RESP RTIME
for ((i=0;i<N;i++)); do
  STATE[i]="…"
  RESP[i]="…"
  RTIME[i]="…"
done

# Initialize temp files
echo "(fetching...)" > "$IP_FILE"
echo "(fetching...)" > "$LOC_FILE"

fetch_ip_location() {
  local ip city country

  # Get IP from reliable plain-text providers
  ip="$(curl -fsS --max-time 4 https://api.ipify.org 2>/dev/null || true)"
  [[ -z "${ip:-}" ]] && ip="$(curl -fsS --max-time 4 https://ifconfig.me/ip 2>/dev/null || true)"

  if [[ -z "${ip:-}" ]]; then
    echo "(unavailable)" > "$IP_FILE"
    echo "(unavailable)" > "$LOC_FILE"
    return
  fi

  echo "$ip" > "$IP_FILE"

  # Get City and Country Name (Plain text endpoints from ipinfo.io)
  city="$(curl -fsS --max-time 4 "https://ipinfo.io/city" 2>/dev/null || true)"
  country="$(curl -fsS --max-time 4 "https://ipinfo.io/country" 2>/dev/null || true)"

  if [[ -n "$city" && -n "$country" ]]; then
    echo "$city, $country" > "$LOC_FILE"
  elif [[ -n "$city" ]]; then
    echo "$city" > "$LOC_FILE"
  else
    echo "(unavailable)" > "$LOC_FILE"
  fi
}

http_check() {
  local url="$1"
  local out code t
  out="$(curl -A "$UA" -o /dev/null -sS -w "%{http_code} %{time_total}" --max-time "$TIMEOUT" "$url" 2>/dev/null || true)"
  code="${out%% *}"
  t="${out#* }"

  if [[ -z "$code" || "$code" == "000" ]]; then
    echo "DOWN|NO_CONN|—"
    return
  fi

  local ms
  ms="$(awk -v tt="$t" 'BEGIN { printf "%.0f", tt*1000 }' 2>/dev/null || echo "—")"

  if [[ "$code" =~ ^2 ]]; then
    echo "UP|${code} OK|${ms}ms"
  elif [[ "$code" =~ ^3 ]]; then
    echo "UP|${code} REDIR|${ms}ms"
  elif [[ "$code" =~ ^4 ]]; then
    echo "DOWN|${code} 4XX|${ms}ms"
  elif [[ "$code" =~ ^5 ]]; then
    echo "DOWN|${code} 5XX|${ms}ms"
  else
    echo "DOWN|${code}|${ms}ms"
  fi
}

# Terminal helpers
HIDE_CURSOR() { printf "\033[?25l"; }
SHOW_CURSOR() { printf "\033[?25h"; }
CLEAR_SCREEN() { printf "\033[2J\033[H"; }
GOTO() { local r="$1" c="$2"; printf "\033[%d;%dH" "$r" "$c"; }
CLR_LINE() { printf "\033[2K"; }

# Layout constants (1-indexed rows)
ROW_TITLE=1
ROW_HEADER=3
ROW_TABLE_HDR=6
ROW_TABLE_SEP=7
ROW_TABLE_FIRST=8
ROW_FOOTER=$((ROW_TABLE_FIRST + N + 1))

draw_frame_once() {
  CLEAR_SCREEN
  HIDE_CURSOR

  GOTO $ROW_TITLE 1; CLR_LINE
  printf "WebMon (top-style)  |  Ctrl+C to quit\n"

  GOTO $ROW_HEADER 1; CLR_LINE
  printf "Date/Time: \n"
  GOTO $((ROW_HEADER+1)) 1; CLR_LINE
  printf "IP:        \n"
  GOTO $((ROW_HEADER+2)) 1; CLR_LINE
  printf "Location:  \n"

  GOTO $ROW_TABLE_HDR 1; CLR_LINE
  printf "%-3s %-45s %-6s %-12s %-12s\n" "#" "URL" "STATE" "RESPONSE" "RESP TIME"
  GOTO $ROW_TABLE_SEP 1; CLR_LINE
  printf "%s\n" "$(printf '%0.s-' {1..85})"

  for ((i=0;i<N;i++)); do
    GOTO $((ROW_TABLE_FIRST+i)) 1; CLR_LINE
    printf "%-3s %-45s %-6s %-12s %-12s\n" "$((i+1))" "${URLS[i]:0:45}" "…" "…" "…"
  done

  GOTO $ROW_FOOTER 1; CLR_LINE
  printf "Checks every %ss | Header updates every %ss | Source: %s\n" "$CHECK_INTERVAL" "$HEADER_INTERVAL" "$WEBSITES_FILE"
}

render_header() {
  local now
  now="$(date "+%Y-%m-%d %H:%M:%S")"

  GOTO $ROW_HEADER 1; CLR_LINE
  printf "Date/Time: %s" "$now"

  GOTO $((ROW_HEADER+1)) 1; CLR_LINE
  printf "IP:        %s" "$(cat "$IP_FILE" 2>/dev/null || echo '(fetching...)')"

  GOTO $((ROW_HEADER+2)) 1; CLR_LINE
  printf "Location:  %s" "$(cat "$LOC_FILE" 2>/dev/null || echo '(fetching...)')"
}

render_row() {
  local idx="$1" url="$2" state="$3" resp="$4" rtime="$5"
  local row=$((ROW_TABLE_FIRST + idx))
  local color_reset="\033[0m"
  local color_up="\033[32m"
  local color_down="\033[31m"

  GOTO "$row" 1; CLR_LINE

  if [[ "$state" == "UP" ]]; then
    printf "%-3s %-45s ${color_up}%-6s${color_reset} %-12s %-12s" "$((idx+1))" "${url:0:45}" "$state" "$resp" "$rtime"
  elif [[ "$state" == "DOWN" ]]; then
    printf "%-3s %-45s ${color_down}%-6s${color_reset} %-12s %-12s" "$((idx+1))" "${url:0:45}" "$state" "$resp" "$rtime"
  else
    printf "%-3s %-45s %-6s %-12s %-12s" "$((idx+1))" "${url:0:45}" "$state" "$resp" "$rtime"
  fi
}

stop() {
  SHOW_CURSOR
  rm -f "$IP_FILE" "$LOC_FILE"
  GOTO $((ROW_FOOTER+2)) 1
  echo
  exit 0
}
trap stop INT TERM

# Background loops
header_loop() {
  while true; do
    render_header
    sleep "$HEADER_INTERVAL"
  done
}

check_loop() {
  while true; do
    # Refresh IP/location periodically in case VPN changes
    fetch_ip_location

    for ((i=0;i<N;i++)); do
      IFS='|' read -r st rp rt <<<"$(http_check "${URLS[i]}")"
      STATE[i]="$st"; RESP[i]="$rp"; RTIME[i]="$rt"
      render_row "$i" "${URLS[i]}" "${STATE[i]}" "${RESP[i]}" "${RTIME[i]}"
    done

    sleep "$CHECK_INTERVAL"
  done
}

# Start
fetch_ip_location
draw_frame_once
render_header

# Run both loops; kill both on exit via trap
header_loop & HL_PID=$!
check_loop & CL_PID=$!

wait "$HL_PID" "$CL_PID"
