package tech.finnlestrange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    /*
    * TODO
    *  - Weird error, fitness value does not correspond to the actual value of genomes path length, therefore reports incorrect minimum values
    *       - e.g. Salesman {genome=[p, q, s, h, j, g, a, m, t, i, o, c, f, r, n, e, k, d, b, l], genomeLength=20, fitness=2297} -> reported as 1777
    *       - Must be modifying an existing genome Collections.swap or something and not updating the fitness value, seems like there is nothing else it could be
    *  - Think I fixed it my copying parent values in the crossover rather than addressing them by assigning them
    * */

    // Used for generating salesmen -> needs city names and distances to create new salesmen => for fitness calculations
    private final List<String> cityNames;
    private final List<List<Integer>> distances;

    // Genetic Algorithm Stuff
    private final int targetFitness; // this is the fitness that we want to reach
    private final int maxIterations; // this is a cut-off so that the algo can manually stop after a certain number of generations

    private List<Salesman> fittest;

    private final int nPerPop; // the number of salesmen to generate per population
    private final int genomeLength; // the length of the genome => cityNames.size() - 1
    private final int reproductionRate; // the number of individuals / salesmen to be reproduced for the next generation
    private final float mutationRate; // frequency of mutations when creating a new generation


    // Constructor
    GeneticAlgorithm(List<String> cityNames, List<List<Integer>> distances) {
        // Set variables
        this.cityNames = cityNames;
        this.distances = distances;

        // Genetic Algo Variables
        this.targetFitness = 1350;
        this.maxIterations = 12000;
        this.nPerPop = 5000;
        this.genomeLength = cityNames.size() - 1;
        this.reproductionRate = 300;
        this.mutationRate = 0.2f;
    }

    public Salesman optimizeStartingGenome(List<String> startingGenome) {
        // create an initial population with the predefined genome
        List<Salesman> population = generateInitialPopulationDefinedGenome(startingGenome);
        return optimizeHelper(population);
    }

    // Main driver code -> runs the algorithm and returns the fittest individual at the end
    public Salesman optimize() {
        // create an initial population
        List<Salesman> population = generateInitialPopulation();
        return optimizeHelper(population);
    }

    // Helper function for the optimization algorithm
    private Salesman optimizeHelper(List<Salesman> population) {
        Salesman bestSalesman = population.get(0); // instantiating the best genome so far
        fittest = new ArrayList<>();

        int currentGeneration = 0;

        for (int i = 0; i < maxIterations; i++) {
            currentGeneration++;
            List<Salesman> selected = selection(population); // select individuals to be reproduced
            population = generatePopulation(selected); // create a new population from previous ones

            if (Collections.min(population).getFitness() < bestSalesman.getFitness()) {
                bestSalesman = Collections.min(population);
                fittest.add(bestSalesman);
            }

            if (i % 100 == 0) System.out.println("On Generation: " + currentGeneration + "\n" + "Best Fitness so far: " + bestSalesman.getFitness() + ", genome: " + bestSalesman.getGenome());

            // if best salesman from current population is better than the overall best, then replace with new best salesman
            if (bestSalesman.getFitness() <= targetFitness) {
                System.out.println("Finished on generation: " + currentGeneration + ", data -> fitness: " + bestSalesman.getFitness() + ", genome: " + bestSalesman.getGenome());
                return Collections.min(fittest);
            }
        }
        return bestSalesman;
    }


    // Genetic algorithm functions
    // ---------------------------
    // Notes: using pmx -> this avoids a repeating city in genome, roulette selection -> avoids local maximums and is better in the long run

    // Selection
    /*
     * Notes
     * - This is a stochastic selection method therefore the probability of an individual being selected is proportional
     *    to the fitness of said individual.
     * - Larger part of the roulette wheel is given to those with a greater fitness, thus more likely to be chosen
     *
     * Formula -> p = fitness / (sum of all fitness of the population)
     *
     * */

    // Helper function to call the roulette selection the number of times specified in the reproductionRate
    private List<Salesman> selection(List<Salesman> population) {
        List<Salesman> selected = new ArrayList<>();
        for (int i = 0; i < reproductionRate; i++) {
            selected.add(rouletteSelection(population));
        }
        return selected;
    }

    private Salesman rouletteSelection(List<Salesman> population) {

        // sum all fitness values in population
        int sumOfFitnessOfPopulation = population.stream().map(Salesman::getFitness).mapToInt(Integer::intValue).sum();

        // pick a random value on the roulette wheel
        Random random = new Random();
        int rouletteValue = random.nextInt(sumOfFitnessOfPopulation);

        // want p of selecting salesman to be inversely proportional, higher fitness, higher change
        float pValue = (float) 1 / rouletteValue;

        // go through population, sum fitness, when larger, return last salesman
        int fitnessTemp = 0;
        for (Salesman salesman : population) {
            fitnessTemp = fitnessTemp + salesman.getFitness();
            if (fitnessTemp >= pValue) return salesman;
        }

        // if condition above is never reached, then return a random value
        return population.get(random.nextInt(population.size()));
    }

    // generic helper function to pick n random individuals from a population -> used for picking random parents in a population
    public <O> List<O> pickNRandomIndividuals(List<O> list, int n) {
        Random random = new Random();
        if (list.size() < n) return null; // cannot pick more elements than are in the list

        for (int i = list.size() - 1; i >= list.size() - n; i--) {
            Collections.swap(list, i, random.nextInt(i + 1));
        }

        return list.subList(list.size() - n, list.size());
    }


    // CROSSOVER CODE
    // --------------
    /*
    * Notes
    * - Using a PMX, uses a random crossover point, swaps elements in place, using locations of elements in the
    *   second list as references of where to swap elements in the first list
    * - This avoids the case where a city would appear twice in the genome of a salesman, as would happen using a simple
    *   crossover method
    * */

    private List<Salesman> crossover(List<Salesman> parents) {
        // Need random to find the point to crossover
        Random random = new Random();

        List<Salesman> children = new ArrayList<>();
        int crossoverLength = random.nextInt(genomeLength); // picks a random pos 1 -> size of the genome to be used for crossover

        // this was the issue all along, was modifying the parents therefore fitness values did not match genome fitness
        List<String> parent1 = new ArrayList<>();
        parent1.addAll(parents.get(0).getGenome());

        List<String> parent2 = new ArrayList<>();
        parent2.addAll(parents.get(1).getGenome());

        // First Child
        for (int i = 0; i < crossoverLength; i++) {
            String currentValue = parent2.get(i);
            Collections.swap(parent1, parent1.indexOf(currentValue), i); // swap values based on parent2's values
        }

        // create child and add to children list
        children.add(new Salesman(parent1, distances, cityNames));

        // reset parent1
        List<String> parent1New = new ArrayList<>();
        parent1New.addAll(parents.get(0).getGenome());

        // Second Child
        for (int i = crossoverLength; i < genomeLength; i++) {
            String currentValue = parent1.get(i);
            Collections.swap(parent2, parent2.indexOf(currentValue), i);
        }

        // add second child
        children.add(new Salesman(parent2, distances, cityNames));

        return children;
    }


    // MUTATION
    /*
    * Notes
    * - Random test, based on mutation rate variable, if random float is greater, then mutate, if not, continue
    * - If mutated, swap two random cities in the salesman's genome
    * */

    private Salesman mutate(Salesman salesman) {
        Random random = new Random();
        float mutateValue = random.nextFloat();
        if (mutateValue > mutationRate) {
            List<String> genome = salesman.getGenome();
            Collections.swap(genome, random.nextInt(genomeLength), random.nextInt(genomeLength));
            return new Salesman(genome, distances, cityNames);
        }
        return salesman;
    }

    // CREATING GENERATIONS / POPULATIONS
    private List<Salesman> generateInitialPopulation() {
        List<Salesman> gen0 = new ArrayList<>();
        for (int i = 0; i < nPerPop; i++) {
            gen0.add(new Salesman(distances, cityNames));
        }
        return gen0;
    }

    private List<Salesman> generateInitialPopulationDefinedGenome(List<String> preDefinedGenome) {
        List<Salesman> gen0 = new ArrayList<>();
        for (int i = 0; i < nPerPop; i++) {
            gen0.add(new Salesman(preDefinedGenome, distances, cityNames));
        }
        return gen0;
    }

    private List<Salesman> generatePopulation(List<Salesman> previousPopulation) {
        List<Salesman> population = new ArrayList<>();

        int currentPopulationSize = 0;
        while (currentPopulationSize < nPerPop) {
            List<Salesman> parents = pickNRandomIndividuals(previousPopulation, 2); // pick 2 random parents from previous population
            List<Salesman> children = crossover(parents); // create 2 children to add to new population

            // create children and maybe mutate if random test passes
            children.set(0, mutate(children.get(0)));
            children.set(1, mutate(children.get(1)));

            population.addAll(children);
            // increase population size by 2 -> added 2 children
            currentPopulationSize = currentPopulationSize + 2;

        }

        return population;
    }



}
