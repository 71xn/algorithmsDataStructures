package tech.finnlestrange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private List<List<Integer>> cities;
    private List<String> cityNames;
    private int numberOfCities;

    private int populationSize;
    private int generationSize;
    private int maxIteration;
    private int genomeSize; // length of the genome arraylist, numCities - 1
    private int reproductionSize;
    private float mutationRate;
    private int targetFitness;

    public GeneticAlgorithm(int numberOfCities, List<List<Integer>> cities, List<String> cityNames, int targetFitness) {
        this.numberOfCities = numberOfCities;
        this.cities = cities;
        this.cityNames = cityNames;
        this.targetFitness = targetFitness;
        this.genomeSize = numberOfCities - 1;

        generationSize = 5000;
        reproductionSize = 200;
        maxIteration = 1000;
        mutationRate = 0.1f;
    }

    public List<Salesman> selection(List<Salesman> population) {

        /*
        * Loops through size of new gene pool and selects the best candidate using roulette selection that I hardly understand
        * */

        List<Salesman> selected = new ArrayList<>();
        for (int i = 0; i < reproductionSize; i++) {
            selected.add(rouletteSelection(population));
        }
        return selected;
    }

    public List<Salesman> generateInitialPopulation() {
        List<Salesman> population = new ArrayList<>();
        for (int i = 0; i < generationSize; i++) {
            population.add(new Salesman(numberOfCities, cities, cityNames));
        }

        return population;
    }


    public List<Salesman> createGeneration(List<Salesman> population) {
        List<Salesman> generation = new ArrayList<>();
        int currentGenerationSize = 0;
        while (currentGenerationSize < generationSize) {
            List<Salesman> parents = pickRandomNElements(population, 2);
            List<Salesman> children = crossover(parents);
            children.set(0, mutate(children.get(0)));
            children.set(1, mutate(children.get(1)));
            generation.addAll(children);
            currentGenerationSize += 2;
        }

        return generation;
    }

    // this is the termination condition that gives us the optimal solution within a certain number of iterations
    public Salesman optimize() {
        List<Salesman> population = generateInitialPopulation();
        Salesman best = population.get(0);
        for (int i = 0; i < maxIteration; i++) {
            List<Salesman> selected = selection(population);
            population = createGeneration(selected);
            best = Collections.min(population);
            if (best.getFitness() < targetFitness) break;
        }
        return best;
    }

    // theory from here -> https://stackoverflow.com/questions/177271/roulette-selection-in-genetic-algorithms
    public Salesman rouletteSelection(List<Salesman> population) {
        // adds up the fitness of the entire population
        int totalFitness = population.stream().map(Salesman::getFitness).mapToInt(Integer::intValue).sum();

        // get random value from 0 - total fitness of the population
        Random random = new Random();
        int chosenValue = random.nextInt(totalFitness);

        // minimizing values therefore we need reciprocal
        // therefore prob of selecting a genome is inversely proportional
        // thus smaller the fitness the higher the probability
        float recValue = (float) 1 / chosenValue;

        float currentTotal = 0;
        for (Salesman genome : population) {
            currentTotal += (float) 1 / genome.getFitness();
            if (currentTotal >= recValue) {
                return genome;
            }
        }

        // if a value did not return above, return a random one below
        return population.get(random.nextInt(generationSize));

    }

    public <O> List<O> pickRandomNElements(List<O> list, int n) {
        Random random = new Random();
        int length = list.size();

        if (length < n) return null; // if list does not have n elements

        for (int i = length - 1; i >= length - n; i--){
            Collections.swap(list, i, random.nextInt(i + 1));
        }

        return list.subList(length - n, length); // returns n random elements

    }

    public List<Salesman> crossover(List<Salesman> parents) {

        /* Notes
        * Cannot do a conventional crossover because individuals cannot visit the same city twice therefore we
        * use the second parent as a seed for swapping values in the first parents genome to create a new child
        * that has non-duplicating elements in their genome.
        * */

        // Pre-requisite
        Random random = new Random();
        int breakpoint = random.nextInt(genomeSize - 2);
        List<Salesman> children = new ArrayList<>(); // to store all the children created by the crossed over parent genomes

        // Make copies of initial parent genomes -> if chosen to crossover we don't want to modify in place
        List<String> parent1Genome = parents.get(0).getGenome().subList(1, 20);
        List<String> parent2Genome = parents.get(1).getGenome().subList(1, 20);

        // create the first child
        for (int i = 0; i < breakpoint; i++) {
            String value = parent2Genome.get(i);
            Collections.swap(parent1Genome, parent1Genome.indexOf(value), i);
        }

        children.add(new Salesman(parent1Genome, numberOfCities, cities, cityNames));

        parent1Genome = parents.get(0).getGenome().subList(1,20);
        for (int i = breakpoint; i < 18; i++) {
            String value = parent1Genome.get(i);
            Collections.swap(parent2Genome, parent2Genome.indexOf(value), i);
        }

        children.add(new Salesman(parent2Genome, numberOfCities, cities, cityNames));

        return children;
    }


    // Mutation if genome passes probability test
    public Salesman mutate(Salesman salesman) {
        Random random = new Random();
        float mutate = random.nextFloat(); // random float to get a random number of mutations
        if (mutate < mutationRate) {
            List<String> genome = salesman.getGenome();

            // Swap two random values
            Collections.swap(genome, random.nextInt(genomeSize), random.nextInt(genomeSize));
            return new Salesman(genome, numberOfCities, cities, cityNames);
        }
        return salesman;
    }

}
