// Bubble sort in Java 16 - Finn Lestrange 2021

package tech.finnlestrange;

public class BubbleSort {

    // Bubble sort compares elements with neighbors to check if their values are in the correct order

    private static int[] bubbleSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) { // Compare neighbors, if larger swap
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return array;
    }

    public static void main(String[] args) {

        int[] array = new int[10];

        for(int i = 0; i < 10; i++) {
            // Random integer generation
            array[i] = (int) Math.floor(Math.random() * 100);
        }

        int[] sorted = bubbleSort(array);
        for (int i : sorted) {
            System.out.println(i + " ");
        }

    }
}
