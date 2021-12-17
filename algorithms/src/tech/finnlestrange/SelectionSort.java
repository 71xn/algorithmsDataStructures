// Selection Sort in Java 16 - Finn Lestrange 2021

package tech.finnlestrange;

/*
* Selection sort
* --------------
*
* Algorithm Info -> In place comparison sorting algorithm that compares the current value to all others in the array
*
* */

public class SelectionSort {

    private static int[] selectionSort(int[] array) {

        int min = 0;
        int temp = 0;

        for (int i = 0; i < array.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < array.length - 1; j++) {
                // This is in ascending order, for descending, swap > for <
                if (array[min] > array[j]) {
                    min = j;
                }
            }

            temp = array[i];
            array[i] = array[min];
            array[min] = temp;

        }

        return array;
    }

    public static void main(String[] args) {

        int[] array = {3, 5, 2, 4, 8, 9};

        int[] newArray = selectionSort(array);

        for (int i : newArray) {
            System.out.println(i + " ");
        }

    }
}
