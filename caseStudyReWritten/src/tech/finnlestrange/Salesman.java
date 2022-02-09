package tech.finnlestrange;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Salesman implements Comparable {

    // required for fitness and genome calculations
    private final List<List<Integer>> distances;
    private final List<String> cityNames;
    private final int numberOfCities;

    // genome and other data
    private List<String> genome; // string of all distances excluding x -> first & end -> x
    private int genomeLength; // same as number of cities, but for readability
    private final int fitness; // total distance

    // No pre-specified genome, for initial population
    Salesman(List<List<Integer>> distances, List<String> cityNames) {
        this.distances = distances;
        this.cityNames = cityNames;
        this.numberOfCities = cityNames.size();

        this.genome = randomGenome();
        this.genomeLength = this.genome.size();
        this.fitness = calculateFitness();
    }

    // Pre-Specified genome, for children
    Salesman(List<String> genome, List<List<Integer>> distances, List<String> cityNames) {
        this.genome = genome;
        this.distances = distances;
        this.cityNames = cityNames;
        this.numberOfCities = cityNames.size();
        this.fitness = calculateFitness();

        this.genomeLength = this.genome.size();
    }

    // generates a random genome
    private List<String> randomGenome() {
        List<String> genome = new LinkedList<>();
        genome.addAll(cityNames);
        Collections.shuffle(genome);
        this.genomeLength = genome.size(); // set the length of the genome, should always be the same
        return genome;
    }


    // FITNESS CALC

    private int getIndexOfCityGivenLetter(String c) {
        c = c.toLowerCase(Locale.ROOT); // makes sure it is really lowercase
        return c.charAt(0) - 96;
    }

    private int distanceFromX(int n) {
        List<Integer> x = distances.get(0);
        return x.get(n);
    }

    private int distanceBetween(int s, int f) {
        List<Integer> sDist = distances.get(s);
        return sDist.get(f);
    }

    public int calculateFitness() {
        int fitness = 0;
        int s = 0, f = 0;

        // add distance from x -> first city
        fitness = fitness + distanceFromX(getIndexOfCityGivenLetter(genome.get(0)));
        //System.out.println("x -> " + genome.get(0) + " = " + fitness);

        for (int i = 0; i < numberOfCities - 1; i++) {
            s = getIndexOfCityGivenLetter(genome.get(i));
            f = getIndexOfCityGivenLetter(genome.get(i + 1));
            int distance = distanceBetween(s, f);
            //System.out.println(genome.get(i) + " -> " + genome.get(i + 1) + " = " + distance);
            fitness = fitness + distance;
        }

        // get distance from last city -> x
        int finalDistance = distanceFromX(f);
        //System.out.println(genome.get(numberOfCities - 1) + " -> " + "x" + " = " + finalDistance);
        fitness = fitness + finalDistance;

        return fitness;
    }

    // GETTERS & SETTERS


    @Override
    public String toString() {
        return "Salesman {" +
                "genome=" + genome +
                ", genomeLength=" + genomeLength +
                ", fitness=" + fitness +
                '}';
    }

    public int getFitness() {
        return fitness;
    }

    public List<String> getGenome() {
        return genome;
    }


    // This is used to compare the fitness of another salesman to this instance of one, 1 >, -1 <, 0 same
    @Override
    public int compareTo(Object o) {
        Salesman salesman = (Salesman) o;
        if (this.fitness > salesman.getFitness()) {
            return 1;
        } else if (this.fitness < salesman.getFitness()) {
            return -1;
        } else {
            return 0;
        }
    }
}
