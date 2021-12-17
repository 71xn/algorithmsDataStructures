// Merge Sort in Java 16 - Finn Lestrange 2021

/*
* Merge Sort Information
* ----------------------
*
* Divide and conquer sorting algorithm -> recursive
*
* Steps:
* - divides into two sub-arrays
* - stop when array has size of 1
* - second helper function merge()
* - merge(left, right, sub-array)
*
* Complexity: O(n log n) -> quasilinear algorithm, faster than quadratic complexity. O(n) space complexity
*
* */

package tech.finnlestrange;

public class MergeSort {

    private static void mergeSort(int[] arr) {

        int length = arr.length;

        if (length <= 1) { // Base case
            return;
        }

        // Splitting up the data
        int middle = length / 2;
        int[] leftArr = new int[middle];
        int[] rightArr = new int[length - middle];

        int i = 0; // left array
        int j = 0; // right array

        for (; i < length; i++) {
            if (i < middle) { // fill left array
                leftArr[i] = arr[i];
            } else { // fill right array
                rightArr[j] = arr[i];
                j++;
            }
        }

        mergeSort(leftArr); // subdivides left array again
        mergeSort(rightArr); // subdivides right array again
        merge(leftArr, rightArr, arr); // merges back together based on original array passed to mergeSort
    }

    private static void merge(int[] leftArr, int[] rightArr, int[] arr) {

        // get size of each sub array based on size of main array
        int leftSize = arr.length / 2;
        int rightSize = arr.length - leftSize;

        int i = 0, l = 0, r = 0; // i -> main arr, l -> left arr, r -> right arr

        // check the conditions for merging
        // --------------------------------
        // left index is less than leftSize and right index is less than rightSize
        while (l < leftSize && r < rightSize) {
            if (leftArr[l] < rightArr[r]) { // comparing left elements to right elements and swapping if necessary
                arr[i] = leftArr[l];
                i++;
                l++;
            } else {
                arr[i] = rightArr[r];
                i++;
                r++;
            }
        }

        // When only one element is remaining
        while (l < leftSize) {
            arr[i] = leftArr[l];
            i++;
            l++;
        }
        while (r < rightSize) {
            arr[i] = rightArr[r];
            i++;
            r++;
        }
    }

    public static void main(String[] args) {

        int[] array = {9,3,5,6,7,8,2};
        System.out.print("before merge sort: ");

        for (int i : array) {
            System.out.print(i + " ");
        }

        System.out.println();

        mergeSort(array);

        System.out.print("after merge sort: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
