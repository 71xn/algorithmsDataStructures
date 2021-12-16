// Time complexity comparison of LinkedList vs. ArrayList (dynamic array) - Finn Lestrange 2021

package tech.finnlestrange;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListComparison {
    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();

        long startTime;
        long endTime;
        long elapsedTime;

        // Populate elements
        for (int i = 0; i < 1000000; i++) {
            linkedList.add(i);
            arrayList.add(i);
        }

        // LinkedList  ======================
        startTime = System.nanoTime();
        linkedList.get(500000);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("total time to search for element 500000: " + elapsedTime); // 4981400 ns

        // ArrayList ========================

        startTime = System.nanoTime();
        arrayList.get(500000);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("total time to search for element 500000: " + elapsedTime); // 5300 ns


        // The arraylist is much faster 5300 ns vs. 4981400 ns
    }
}
