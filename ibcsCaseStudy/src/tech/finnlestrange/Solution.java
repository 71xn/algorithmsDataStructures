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
        System.out.println(cities.toString());
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

        // Genetic algo method calls
        float start = System.currentTimeMillis();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(20, distances, cities, 1400);
        Salesman optimal = geneticAlgorithm.optimize();
        float total = System.currentTimeMillis() - start;
        System.out.println(optimal);
        System.out.println("number of generations " + geneticAlgorithm.getNumberGenerations());
        System.out.println();
        System.out.println("Total time: " + total + "ms -> " + (total / 0.001f) / 60 + " minutes.");
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
