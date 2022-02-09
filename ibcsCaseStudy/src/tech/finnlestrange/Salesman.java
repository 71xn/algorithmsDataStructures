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
        this.fitness = 0;
        this.genome = userDefGenome;
        this.cities = cities;
        this.nCities = numberOfTotalCities;
        this.cityNames = cityNames;

        this.fitness = this.calcFitness();
    }


    // Generate Random Genome
    private List<String> randomGenome() {
       List<String> gene = cityNames;
       Collections.shuffle(gene);
       return gene;
    }

    // returns the index that the data for a current city will be at in the cities list
    private int getIndex(String x) {
        // if (x == "x") return 0;
        x = x.toLowerCase(Locale.ROOT);
        int city = (int) x.charAt(0); // convert to char
        int value = 96; // offset for lowercase chars
        return city - value;
    }


    public int calcFitness() {
        int fitness = 0;

        // get x distance to current city -> first in the list
        String first = this.genome.get(0);
        List<Integer> cRow = cities.get(0);
        fitness += cRow.get(getIndex(first));

        for (int i = 0; i < genome.size() - 1; i++) {
            String current = this.genome.get(i);
            String next = this.genome.get(i + 1);
            int indexOfCurrentCity = getIndex(current);
            int indexOfNextCity = getIndex(next);
            cRow = cities.get(indexOfCurrentCity);
            fitness += cRow.get(indexOfNextCity);
        }

        // get distance from last city -> x
        String last = genome.get(genome.size() - 1);
        fitness += cRow.get(getIndex(last));

        this.fitness = fitness;

        return fitness;
    }

    public void testFitness() {
        int fitness = 0;

        // get x distance to current city -> first in the list
        String first = genome.get(0);
        int firstIndex = getIndex(first);
        List<Integer> cRow = cities.get(0);
        System.out.println("x" + " -> " + first + " = " + cRow.get(firstIndex));
        fitness += cRow.get(firstIndex);

        for (int i = 0; i < genome.size() - 1; i++) {
            String current = genome.get(i);
            String next = genome.get(i + 1);
            int indexOfCurrentCity = getIndex(current);
            int indexOfNextCity = getIndex(next);
            List<Integer> currentRow = cities.get(indexOfCurrentCity);
            System.out.println(current + " -> " + next + " = " + currentRow.get(indexOfNextCity));
            fitness += currentRow.get(indexOfNextCity);
        }

        // get distance from last city -> x
        String last = genome.get(genome.size() - 1);
        int lastIndex = getIndex(last);
        System.out.println(last + " -> " + "x" + " = " + cRow.get(lastIndex));
        fitness += cRow.get(lastIndex);
        System.out.println("total fitness: " + fitness);
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
        return genome.toString() + ", fitness: " + this.calcFitness();
    }

    // Allows us to compare fitness of different salesmen
    @Override
    public int compareTo(Object o) {
        Salesman genome = (Salesman) o;
        if (this.fitness > genome.getFitness()) {
            return 1;
        } else if (this.fitness < genome.getFitness()) {
            return -1;
        } else {
            return 0;
        }
    }
}
