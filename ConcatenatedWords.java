import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ConcatenatedWords {
    private static Trie trie = new Trie();

    public static void processFile(String fileName) {
        try {
            long startTime = System.currentTimeMillis(); // Start time

            List<String> words = Files.readAllLines(Paths.get(fileName));
            words.forEach(trie::insert);

            String longest = "", secondLongest = "";

            for (String word : words) {
                if (prefixChecker(word, true)) {
                    if (word.length() > longest.length()) {
                        secondLongest = longest;
                        longest = word;
                    } else if (word.length() > secondLongest.length()) {
                        secondLongest = word;
                    }
                }
            }

            long endTime = System.currentTimeMillis(); // End time
            long timeTaken = endTime - startTime; // Calculate duration

            System.out.println("Longest Compound Word: " + longest);
            System.out.println("Second Longest Compound Word: " + secondLongest);
            System.out.println("Time taken to process file: " + timeTaken + " milliseconds\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean prefixChecker(String word, boolean isOriginal) {
        if (word.isEmpty())
            return !isOriginal;

        TrieNode node = trie.getRoot();
        for (int i = 0; i < word.length(); i++) {
            node = node.children.get(word.charAt(i));
            if (node == null)
                return false;
            if (node.end) {
                if (prefixChecker(word.substring(i + 1), false)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        processFile("Input_01.txt");
        processFile("Input_02.txt");
    }
}
