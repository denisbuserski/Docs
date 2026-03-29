class Solution {
    public int lengthOfLongestSubstring(String input) {
        int currentLongestSubstring = 0;

        List<Character> readLetters = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char currentLetter = input.charAt(i);

            if (!readLetters.contains(currentLetter)) {
                readLetters.add(currentLetter);
            } else {
                currentLongestSubstring = Math.max(currentLongestSubstring, readLetters.size());
                readLetters.clear();

                int index = i;
                readLetters.add(currentLetter);

                while(currentLetter != input.charAt(index - 1)) {
                    readLetters.add(input.charAt(index - 1));
                    index--;
                }
            }
        }

        return Math.max(currentLongestSubstring, readLetters.size());
    }
}
