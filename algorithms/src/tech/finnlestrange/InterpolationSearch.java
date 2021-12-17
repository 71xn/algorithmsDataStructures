// Interpolation Search in Java 16 - Finn Lestrange 2021

package tech.finnlestrange;

/*
* Algorithm info:
* - Improvement over binary search, used for uniformly distributed data guesses where a value might be based on
*   calculated results. If incorrect then search area is narrowed and a new guess is calculated
*
* Complexity:
* - Average case: O(log(log n )), worst case: O(n) value increases exponentially
*
* */


public class InterpolationSearch {

    private static int interpolationSearch(int[] array, int value) {

        // get start and end index
        int high = array.length - 1;
        int low = 0;

        // While the value is greater than or equal to the value at the lower bound, & the value is less than or equal
        // to the value at the upper bound and low less than or equal to high
        while(value >= array[low] && value <= array[high] && low <= high) {
            /*
            * How the search works:
            * ---------------------
            * - The guess for the position of an element is called the probe
            * - probe returns a higher value when element being searched for is closer to array[high]
            * - probe returns a smaller value when element being searched for is closer to array[low]
            * */

           /*
           * Formula Derivation:
           * -------------------
           * Assume elements are linearly distributed, common difference between elements, arithmetic series progression
           *
           * General equation of a line is y = mx + c
           * y is value in array and x is index of y value
           *
           * array[high] = m * high + c -- 1
           * array[low] = m * low + c   -- 2
           * x = m * position = c       -- 3
           *
           * m = (array[high] - array[low]) / (high - low)
           *
           * Subtract 2 from 3
           * x - array[low] = m * (position - low)
           * low + (x - array[low]) / m = position
           *
           * finally
           *
           * position = low + (x - array[low]) * (high - low) / (array[high] - array[low])
           *
           * */

            int probe = low + (high - low) * (value - array[low]) / (array[high] - array[low]);
            System.out.println("probe: " + probe);

            // Check for position
            if (array[probe] == value) return probe;
            else if (array[probe] > value) low = probe + 1; // Disregards whole chunk of data lower than value
            else high = probe - 1; // Disregards whole chunk of data higher than value
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        int value = 5;

        int index = interpolationSearch(array, 5);
        System.out.println("element " + value + " found at index: " + index);

    }
}
