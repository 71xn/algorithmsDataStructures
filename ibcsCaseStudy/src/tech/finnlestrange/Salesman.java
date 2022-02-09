package tech.finnlestrange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Salesman implements Comparable {

    // Instance Variables
    List<String> genome; // stores the list of cities in order of visited
    List<List<Integer>> cities;
    List<String> cityNames; // stores the names of the cities

    int nCities; // stores the number of cities
    int fitness; // stores the fitness of this current salesman


    // Constructor for a random genome
    Salesman(int numberOfTotalCities, List<List<Integer>> cities, List<String> cityNames) {
        this.cities = cities;
        this.nCities = numberOfTotalCities;
        this.cityNames = cityNames;

        this.genome = randomGenome();
        this.fitness = this.calcFitness();
    }


    // Constructor for a pre-specified genome
    Salesman(List<String> userDefGenome, int numberOfTotalCities, List<List<Integer>> cities, List<String> cityNames) {
        this.cities = cities;
        this.nCities = numberOfTotalCities;
        this.cityNames = cityNames;

        this.genome = userDefGenome;
        this.fitness = this.calcFitness();
    }


    // Generate Random Genome
    private List<String> randomGenome() {
       List<String> gene = cityNames;
       List<String> start = new ArrayList<>();
       start.add("x");
       Collections.shuffle(gene);
       start.addAll(gene);
       start.add("x");
       return start;
    }

    // returns the index that the data for a current city will be at in the cities list
    private int getIndex(String x) {
        if (x == "x") return 0;
        x = x.toLowerCase(Locale.ROOT);
        int city = (int) x.charAt(0); // convert to char
        int value = 96; // offset for lowercase chars
        return city - value;

    }


    private int calcFitness() {
        int fitness = 0;

        for (int i = 0; i < genome.size() - 1; i++) {
            String current = genome.get(i);
            String next = genome.get(i + 1);
            int indexOfNextCity = getIndex(next);
            int indexOfCurrentCity = getIndex(current);
            List<Integer> currentRow = cities.get(indexOfCurrentCity);
            fitness += currentRow.get(indexOfNextCity);
        }

        return fitness;
    }


    public int getFitness() {
        return this.fitness;
    }

    public List<String> getGenome() {
        return genome;
    }

    // toString Override
    @Override
    public String toString() {
        return genome.toString() + ", fitness: " + this.fitness;
    }

    // Allows us to compare fitness of different salesmen
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
