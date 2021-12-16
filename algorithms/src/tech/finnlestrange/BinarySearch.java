// Binary Search in Java 16 - Finn Lestrange 2021

// Search algo that find pos of data in sorted structure, half of data eliminated at each step of sort

package tech.finnlestrange;

import java.util.Arrays;

public class BinarySearch {


    private int search(int[] array, int data) {

        int low = 0; // start of array
        int high = array.length - 1; // final index of array

        while (low <= high) {
            int middle = low + (high - low) / 2; // Gets value halfway between low and high, low + size of array / 2
            int value = array[middle];
            System.out.println("middle: " + middle);

            if (value < data) low = middle + 1; // disregard lower half of data if value is less than target data value
            else if (value > data) high = middle - 1; // disregards upper half of data if value is higher that the target
            else return middle; // if found
        }
        return -1;
    }


    public BinarySearch() {
        int array[] = new int[100];
        int target = 32;
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        // Using built in method
        System.out.println(Arrays.binarySearch(array, target));
        System.out.println(search(array, target));
    }

    public static void main(String[] args) {
        new BinarySearch();
    }
}
