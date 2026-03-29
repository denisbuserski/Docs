class Solution {
    public int lengthOfLongestSubstring(String input) {
        Map<Character, Integer> readLetters = new HashMap<>();
        int count = 0;
        int left = 0;

        for (int right = 0; right < input.length(); right++) {
            char letter = input.charAt(right);

            if (readLetters.containsKey(letter) && readLetters.get(letter) >= left) {
                left = readLetters.get(letter) + 1;
            }

            readLetters.put(letter, right);
            count = Math.max(count, right - left + 1);
        }

        return count;
    }
}
