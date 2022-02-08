package tech.finnlestrange;

public class Member {
    private int distance;
    private String[] cities;

    // Constructor to instantiate a new member of a generation with genes and a fitness
    Member(String[] cities, int distance) {
        this.cities = cities; // this is essentially the gene of the generation member
        this.distance = distance; // this is the fitness of the member
    }

    // Getters
    public int getDistance() {
        return distance;
    }

    public String[] getCities() {
        return cities;
    }
}
