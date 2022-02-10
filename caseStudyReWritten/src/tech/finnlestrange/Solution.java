package tech.finnlestrange;

        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.*;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;

public class Solution {

    // Globals
    private List<List<Integer>> distances = new ArrayList<>();
    private List<String> cities = new ArrayList<>();

    private void populateCities() {
        for (int i = 0; i < 20; i++) {
            cities.add((char)('a' + i) + "");
        }
    }

    // reads data from the csv file in the following format
    // | |X|A|B|
    // |X|0 9 7
    // |A|9 0 4
    // |B|7 4 0

    private void readData(String fileToRead) throws IOException {
        // Read from CSV File and add to global distances array

        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\n");
                String[] digits = values[0].split(",");
                //System.out.println(Arrays.stream(digits).mapToInt(Integer::parseInt).boxed().toList());
                distances.add(Arrays.stream(digits).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printData() {
        System.out.println(cities.toString() + ", number of cities: " + cities.size());
        for (int i = 0; i < distances.size(); i++) {
            System.out.println(distances.get(i).toString());
        }
    }


    // Driver Code // IGNORE
    Solution() throws IOException {

        // Pre-requisite code
        String fileToRead = "cities.csv";
        readData(fileToRead);
        populateCities();
        //printData();

        // TEST CODE w/ known good solution
/*
        List<String> check1 = new ArrayList<>(Arrays.asList("n","e","o","r","k","m","f","b","a","g","d","h","c","l","q","j","i","t","s","p")); // -> 1791
        List<String> check3 = new ArrayList<>(Arrays.asList("t","b","j","i","c","l","q","e","n","d","g","s","a","h","m","k","f","p","r","o")); // -> 1483
        List<String> check2 = new ArrayList<>(Arrays.asList("k","t","p","e","d","n","f","o","c","b","r","h","m","g","a","i","l","q","j","s")); // -> 1706
        List<String> test = new ArrayList<>(Arrays.asList("k","a","j","i","f","o","p","q","h","n","g","b","d","r","m","t","e","l","s","c"));
        List<String> check4 = new ArrayList<>(Arrays.asList("s", "g", "a", "m", "h", "k", "r", "o", "i", "t", "d", "n", "e", "j", "q", "l", "c", "b", "f", "p"));
        List<String> check = new ArrayList<>(Arrays.asList("s","g","a","m","h","f","r","o","i","t","n","d","e","j","q","l","c","b","k","p"));

        Salesman salesman = new Salesman(check , distances, cities);
        System.out.println(salesman);
*/
/*

        // Actual Algorithm Code
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(cities, distances);
        Salesman solution = geneticAlgorithm.optimize();
        System.out.println(solution);
*/

        // Algorithm with predefined starting genome
        //List<String> predefined = new ArrayList<>(Arrays.asList("n","a","e","d","j","t","i","l","q","o","c","b","r","k","f","g","s","m","h","p"));
        // List<String> predefined = new ArrayList<>(Arrays.asList("s", "g", "a", "m", "h", "k", "r", "o", "i", "t", "d", "n", "e", "j", "q", "l", "c", "b", "f", "p"));
        List<String> predefined = new ArrayList<>(Arrays.asList("s","g","a","m","h","f","r","o","i","t","d","n","e","j","q","l","c","b","k","p"));

        GeneticAlgorithm algorithm = new GeneticAlgorithm(cities, distances);
        Salesman s = algorithm.optimizeStartingGenome(predefined);
        System.out.println(s);

    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
