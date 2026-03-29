class Solution {
    public int lengthOfLongestSubstring(String input) {
        int count = 0;
        int left = 0;

        for (int right = 0; right < input.length(); right++) {
            int indexOfFirstAppearanceInSubstring = input.indexOf(input.charAt(right), left);
            
            if (indexOfFirstAppearanceInSubstring != right) {
                left = indexOfFirstAppearanceInSubstring + 1;
            }
            
            count = Math.max(count, right - left + 1);
        }
        return count;
    }
}
