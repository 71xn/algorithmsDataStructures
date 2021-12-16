// Java Queue Implementation - Finn Lestrange 2021

/*
* Queue Info:
*
* FIFO - First in, first out
*
* */

package tech.finnlestrange;

import java.util.LinkedList;
import java.util.Queue;

public class JavaQueue {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();

        // is empty?
        System.out.println(queue.isEmpty()); // True

        queue.offer("bob"); // Adds element to queue
        System.out.println(queue.poll()); // removes element

        queue.offer("bob");

        // is empty?
        System.out.println(queue.isEmpty()); // False

        // Size - length of queue
        System.out.println(queue.size()); // 1

    }
}
