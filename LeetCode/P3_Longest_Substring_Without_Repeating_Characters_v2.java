class Solution {
    public int lengthOfLongestSubstring(String input) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        int left = 0;

        for (int right = 0; right < input.length(); right++) {
            char letter = input.charAt(right);

            while (set.contains(letter)) {
                set.remove(input.charAt(left));
                left++;
            }

            set.add(letter);
            count = Math.max(count, right - left + 1);
        }
        return count;
    }
}
