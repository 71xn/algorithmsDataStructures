// Linear Search in Java 16 - Finn Lestrange 2021

package tech.finnlestrange;

public class LinearSearch {
    public static void main(String[] args) {

        int[] array = {1, 4, 5, 2, 8};

        int index = linearSearch(array, 1);
        System.out.println(index);

    }

    private static int linearSearch(int[] array, int value) {
        for (int i = 0; i< array.length; i++) {
            if (array[i] == value) return i;
        }
        return -1;
    }
}
