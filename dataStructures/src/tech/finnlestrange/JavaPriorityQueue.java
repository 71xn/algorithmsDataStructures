package tech.finnlestrange;

// This is the same as a regular queue, but elements with a higher priority (lower value) are served first
// It is good for ordering things in ascending orders (usually integer or floats)

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class JavaPriorityQueue {

    public static void main(String[] args) {

        // This sorts in ascending order
        Queue<Integer> queue = new PriorityQueue<>();

        // This sorts in descending order add Collections.reverseOrder() to the constructor
        Queue<Integer> queueDesc = new PriorityQueue<>(Collections.reverseOrder());

        // Adding elements
        queue.offer(10);
        queue.offer(17);
        queue.offer(4);
        queue.offer(1);

        // is empty?
        System.out.println(queue.isEmpty()); // False

        // Removing elements
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        };

        // is empty?
        System.out.println(queue.isEmpty()); // True

    }

}
