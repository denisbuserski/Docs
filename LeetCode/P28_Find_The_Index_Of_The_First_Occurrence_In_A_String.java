class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack.contains(needle)) {
            int needleLength = needle.length();

            for (int i = 0; i < haystack.length(); i++) {
                String subStringPart = haystack.substring(i, i + needleLength);
                if (subStringPart.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
        
    }
}
