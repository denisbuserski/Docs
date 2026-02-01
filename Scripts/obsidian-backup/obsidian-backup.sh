#!/bin/bash

# Path to your Obsidian vault
VAULT_PATH="/home/user/Desktop/"
LOG_FILE="/home/user/Desktop/obsidian-backup.log"

# Ensure the SSH agent is running and the key is loaded
eval "$(ssh-agent -s)" # Start the SSH agent, which is responsible for holding your SSH keys in memory and providing them to SSH-based applications like Git. Without this command the "ssh-add" will fail if there is no running SSH agent.
ssh-add ~/.ssh/id_ed00000 # Add you SSH private key to the running  agent.
# With the command "ssh-add -l" you can see if the key is loaded into the agent

# Function to log messages
# "$1" refers to the first argument passed to the function when it is called
log_message() {
	echo "$(date "+%Y-%m-%d %H:%M:%S") - $1" >> "$LOG_FILE"
}

# Navigate to your vault
# "|| {}" if the "cd" command fails log an error
cd "$VAULT_PATH" || {
	log_message "ERROR: Could not navigate to $VAULT_PATH"
}



# Check if there are any changes to commit
# "git status --porcelain" this command checks the status of the git repository and gives a concise output
# "grep -q ." this checks if there is any output from "git status --porcelain", if there are changes, the command will return true, and the script will proceed to commit and push
if git status --porcelain | grep -q .; then
    # Add all changes
    git add .

    # Get current timestamp for commit message
    TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

    # Commit changes with timestamp
    git commit -m "Automatic backup: $TIMESTAMP"

    # Push to GitHub
    if git push origin main; then
        log_message "SUCCESS: Backup completed successfully"
    else
        log_message "ERROR: Failed to push to GitHub"
    fi
else
    log_message "INFO: No changes to commit"
fi
