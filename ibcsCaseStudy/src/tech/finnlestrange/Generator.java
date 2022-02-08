package tech.finnlestrange;

import java.util.List;
import java.util.Random;

public class Generator {

    private boolean duplicate(String[] gene, String current, int currentSize) {
        for (int i = 0; i < currentSize; i++) {
            if (gene[i] == current) return true;
        }
        return false;
    }

    // Code from here; https://stackoverflow.com/questions/27117193/random-integers-between-2-values#27117218
    private int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public String[] generateRandomGene(List<String> cities, int size) {

        String[] gene = new String[size];

        for (int i = 0; i < size; i++) {
            String current = cities.get(random(0, size - 1));
            gene[i] = current;
            while (duplicate(gene, current, i + 1)) {
                current = cities.get(random(0, size - 1));
                gene[i] = current;
            }
        }

        return gene;
    }


    public int getDistance(String[] gene) {



        return -1;
    }

}
