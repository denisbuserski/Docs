import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word1 = scanner.nextLine();
        String word2 = scanner.nextLine();

        boolean doMatch = isAnagram(word1, word2);
        System.out.println(doMatch ? "Anagrams" : "Not Anagrams");
    }

    private static boolean isAnagram(String word1, String word2) {
        if (word1 == null || word2 == null || word1.length() != word2.length()) {
            return false;
        }

        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        int[] counts = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            counts[word1.charAt(i) - 'a']++;
            counts[word2.charAt(i) - 'a']--;
        }

        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
