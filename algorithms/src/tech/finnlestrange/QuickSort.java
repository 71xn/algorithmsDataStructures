// QuickSort Algorithm in Java 16 - Finn Lestrange 2021

/*
* QuickSort Information
* ---------------------
*
* How it works:
* - Need a collection
* - Pass array as argument
* - Pick a pivot point (beginning, middle or end) usually end
* - Pick final resting place of pivot, use j (start of array), i (1 less than beginning)
* - Use temp to swap values
* - check to see if value of j is less than pivot, if it is, increment i, swap i and j
* - when j reaches pivot, we know final resting place of pivot is i + 1
*   - Increment i, swap i and pivot
* - Now, all elements left of pivot are smaller, all elements to right of pivot is greater than or equal to pivot
* - Create two sub-arrays, one with all values left of pivot, one with all values right of pivot
* - Pass sub-arrays as arguments to quicksort
*
* Complexity: (Time)
* - Best Case -> O(n log n)
* - Average Case -> O(n log n)
* - Worst case (if array almost sorted) -> O(n^2)
*
* Complexity: (Space)
* - O(n)
*
* */

package tech.finnlestrange;

public class QuickSort {

    private static void quickSort(int[] arr, int start, int end) {

        // base case
        if (end <= start) { // When array cannot be divided any further
            return;
        }

        int pivot = partition(arr, start, end);
        quickSort(arr, start, pivot - 1); // pivot - 1, end of left partition
        quickSort(arr, pivot + 1, end); // pivot + 1, start of right partition

    }

    // returns location of pivot
    private static int partition(int[] arr, int start, int end) {

        int pivot = arr[end];

        int i = start - 1;
        for (int j = start; j <= end - 1; j++) {
            if (arr[j] < pivot) { // numbers less than pivot need to be on left of pivot, larger on the right
                i++;
                // variable swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        i++;

        // insert pivot into final place
        int temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;

        // "i" is the location of the pivot
        return i;
    }



    public static void main(String[] args) {
        int[] arr = {9, 3, 5, 8, 10, 2, 7};

        quickSort(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + " ");
        }

        // [2, 3, 5, 7, 8, 9, 10] -> sorted :)

    }
}
