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
        for (int i = 0; i < 19; i++) {
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
        System.out.println(cities.toString());
        for (int i = 0; i < distances.size(); i++) {
            System.out.println(distances.get(i).toString());
        }
    }


    // Driver Code // IGNORE
    Solution() throws IOException {
        String fileToRead = "cities.csv";
        readData(fileToRead);
        populateCities();

        // generating first parent generation
        Salesman sTest = new Salesman(21, distances, cities);
        System.out.println(sTest.getGenome().subList(1,20).size());


        Salesman sTest2 = new Salesman(21, distances, cities);
        System.out.println(sTest2.toString());

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(21, distances, cities, 1900);
        Salesman optimal = geneticAlgorithm.optimize();
        System.out.println(optimal);
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }
}