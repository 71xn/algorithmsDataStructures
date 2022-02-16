package tech.finnlestrange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {

    List<String> words;
    Map<String, Float> frequencies;

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

    private void readFrequencies(String filename) {
        frequencies = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // split line at comma
                String[] s = line.split("[,]", 0);
                // each line will have two parts, first is string, second is integer of frequency
                frequencies.put(s[0], Float.valueOf(s[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // sort
        Collections.sort(words);
    }

    // rank words based on their frequencies
    private List<String> rankWords(List<String> words) {
        List<String> rankedWords = new LinkedList<>();
        Map<Float, String> ranked = new TreeMap<>(); // to sort by natural order
        List<Float> rankingValues = new LinkedList<>();
        for (String word : words) {
            rankingValues.add(frequencies.get(word));
            ranked.put(frequencies.get(word), word);
        }

        Collections.sort(rankingValues);

        for (Float f : rankingValues) {
            String s = ranked.get(f);
            rankedWords.add(s);
        }

        // best words are at the end, need to reverse
        Collections.reverse(rankedWords);

        return rankedWords;

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


    private List<String> optimizeLikelyWord(String known, String not, Map<Integer, String> knownInPos, Map<Integer, String> knownButNotInPos) {

        /*
        * known is a string that has all letters known but not in position
        * not is a string that has all the letters known to not be in the word
        * knownInPos stores the position of known letters if user chooses to add them
        * */


        // optimizing for words with only known characters
        List<String> potentialWords = new LinkedList<>();
        for (String word : words) {
            if (checkString(known, word)) {
                potentialWords.add(word);
            }
        }

        // removing words that contain characters known not to be in the word
        List<String> temp = new LinkedList<>();
        temp.addAll(potentialWords);
        for (String word : temp) {
            for (char c : not.toCharArray()) {
                String letter = "" + c;
                if (word.contains(letter)) potentialWords.remove(word);
            }
        }

        // removing words that contain characters in a position we know they are not
        List<String> temp1 = new LinkedList<>();
        temp1.addAll(potentialWords);
        for (String word : temp1) {
            for (Integer key : knownButNotInPos.keySet()) {
                String value = knownButNotInPos.get(key);
                if (word.charAt(key) == (value.charAt(0))) potentialWords.remove(word);
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
        Map<Integer, String> knownButNotInPos = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001B[106m" + "\u001B[90m" + "Wordle Solver" + "\u001B[0m");
        System.out.print("\nPlease enter all known letter, regardless of if they are in the correct place, ex. abc : ");

        String known = scanner.nextLine();
        known = known.toLowerCase(Locale.ROOT);

        // positions of letters in words but in wrong position
        System.out.println();
        System.out.println("Please enter the letters that are in the word, but in the wrong position: ");
        for (int i = 0; i < 5; i++) {
            System.out.print("Right letter wrong position (" + (i + 1) + ") : ");
            String line = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (line == "" || line == null || line == "\n") continue;
            else knownButNotInPos.put(i, line);
        }

        System.out.print("\nPlease input the letters you know are not in the word ex. hyg : ");

        String not = scanner.nextLine();
        not = not.toLowerCase(Locale.ROOT);

        System.out.println();
        System.out.println("Please input the letters you know the positions of: ");

        for (int i = 0; i < 5; i++) {
            System.out.print("Letter in position (" + (i + 1) + ") : ");
            String line = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (line == "" || line == null || line == "\n") continue;
            else knownInPos.put(i, line);
        }

        for (int i = 0; i < knownInPos.size(); i++) if (knownInPos.get(i) == "" || knownInPos.get(i) == null) knownInPos.remove(i);

        System.out.println("\nPotential Solutions (ranked based on frequency in the english language): ");
        List<String> w = optimizeLikelyWord(known, not, knownInPos, knownButNotInPos);
        w = rankWords(w); // ranks the words based on their
        System.out.println(w);
    }

    public Solution() {
        // read data
        readWords("words.txt");
        readFrequencies("frequency.txt");

        // System.out.println(frequencies.get("which"));

        while (true) {
            output();
            System.out.println();
            System.out.println("Would you like to run the code again with new letters: y or n");
            Scanner s = new Scanner(System.in);
            String line = s.nextLine().toLowerCase(Locale.ROOT);
            if (line.contains("y")) continue;
            else if (line != "y") {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Solution();
    }
}
