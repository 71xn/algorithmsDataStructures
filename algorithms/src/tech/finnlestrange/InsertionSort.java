// Insertion Sort in Java 16 - Finn Lestrange 2021

/*
* Insertion Sort Info:
* --------------------
*
* Process
* - Take value at index 1, move to temp
* - Examine elements to left, if larger than temp shift to right
* - Insert temp at open position
* - Increase temp by 1 and continue
*
* Complexity: O(n^2) because there are two loops
* Best Case: O(n)
* Fewer steps than bubble sort
*
* small data set: decent
* large data set: BAD
*
* Use insertion sort over bubble or selection, has much better best case
*
* */

package tech.finnlestrange;

public class InsertionSort {

    private static int[] insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1; // Check value to left of i

            // Check all values to the left of value at position i and swap if current element is larger than temp
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j]; // Shifts element to right
                j--; // Decrement j to check next element
            }
            array[j + 1] = temp;
        }


        return array;
    }

    public static void main(String[] args) {
        int[] array = {7, 4, 3, 8, 9, 8, 2, 1};

        array = insertionSort(array);

        System.out.print("[");
        for (int i : array) {
            System.out.print(i + ", ");
        }
        System.out.print("]");

        // [1, 2, 3, 4, 7, 8, 8, 9]

    }
}
