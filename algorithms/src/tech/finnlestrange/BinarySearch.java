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
            if (value < data) low = middle + 1; // disregard lower half of data if value is less than target data value
            else if (value > data) high = middle - 1; // disregards upper half of data if value is higher that the target
            else return middle; // if found
        }
        return -1;
    }

    private int binarySearchRecursive(int[] array, int lower, int upper, int target) {
        if (upper >= lower) {
            int middle = lower + (upper - lower) / 2;
            if (array[middle] == target) { // if element is found, base case
                return middle;
            } else if (array[middle] < target) { // drop lower half, element in upper
                return binarySearchRecursive(array, middle + 1, upper, target);
            } else if (array[middle] > target) { // drop upper half, element in lower
                return binarySearchRecursive(array, lower, middle - 1, target);
            }
        }

        return -1; // only reached if element is not found
    }


    public BinarySearch() {
        int array[] = new int[100];
        int target = 32;
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        // Using built in method
        long ts = System.nanoTime();
        System.out.println(Arrays.binarySearch(array, target));
        System.out.println(System.nanoTime() - ts + " ns");
        System.out.println();
        System.out.println("iterative");
        ts = System.nanoTime();
        System.out.println(search(array, target));
        System.out.println(System.nanoTime() - ts + " ns");
        System.out.println();
        System.out.println("recursive");
        ts = System.nanoTime();
        System.out.println(binarySearchRecursive(array,0, array.length - 1, target));
        System.out.println(System.nanoTime() - ts + " ns");
    }

    public static void main(String[] args) {
        new BinarySearch();
    }
}
