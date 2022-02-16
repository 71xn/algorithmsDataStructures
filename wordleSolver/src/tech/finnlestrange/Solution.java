package tech.finnlestrange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {

    List<String> words;

    private void readWords(String filename) {

        words = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.toLowerCase(Locale.ROOT));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // checks if all chars in string s1 are in string s2
    private boolean checkString(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (s2.indexOf(c) == -1) { // not in string
                return false;
            }
        }

        return true;
    }

    // check the position of a letter in a word
    private boolean checkPos(String s1, String letter, int index) {
        if (letter == "" || letter == null) return false;
        char c = s1.charAt(index);
        if (c == letter.charAt(0)) return true;
        return false;
    }


    private List<String> optimizeLikelyWord(String known, Map<Integer, String> knownInPos) {

        /*
        * known is a string that has all letters known but not in position
        * */

        readWords("words.txt");

        Collections.sort(words);

        // optimizing for words with only known characters
        List<String> potentialWords = new LinkedList<>();
        for (String word : words) {
            if (checkString(known, word)) {
                potentialWords.add(word);
            }
        }

        // optimizing for letters in certain positions
        List<String> toRemove = new LinkedList<>();
        for (String word : potentialWords) {
            for (Integer key: knownInPos.keySet()) {
                String value = knownInPos.get(key);
                if (checkPos(word, value, key) == false) toRemove.add(word);
                else continue;
            }
        }

        for (String word : toRemove) potentialWords.remove(word);

        return potentialWords;

    }

    public void output() {
        Map<Integer, String> knownInPos = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wordle Solver: ");
        System.out.print("\nPlease input the letters you know with no spaces: ");

        String known = scanner.nextLine();
        known = known.toLowerCase(Locale.ROOT);

        System.out.println("Please input the letters you know the positions of: ");

        for (int i = 0; i < 5; i++) {
            System.out.print("Letter in position (" + (i + 1) + ") : ");
            String line = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (line == "" || line == null || line == "\n") continue;
            else knownInPos.put(i, line);
        }


        for (int i = 0; i < knownInPos.size(); i++) if (knownInPos.get(i) == "" || knownInPos.get(i) == null) knownInPos.remove(i);

        System.out.println("\nPotential Solutions: ");
        List<String> w = optimizeLikelyWord(known, knownInPos);
        System.out.println(w);

    }

    public Solution() {
        output();
    }

    public static void main(String[] args) {
        new Solution();
    }
}
