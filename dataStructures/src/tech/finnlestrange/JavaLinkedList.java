// Java 16 LinkedList Implementation - Finn Lestrange 2021

// LinkedList Info
/*
Singly linked, made up of data, and pointer to next object
Doubly linked, made up of data, pointer to previous object, and pointer to next object

advantages:
dynamic data structure: size grows as needed and does not take up extra memory space
easy insertion and deletion of nodes - O(n) complexity
low memory waste

Can be used in:
implement stack and queues
navigation systems
playlists
*/


package tech.finnlestrange;

import java.util.LinkedList;

public class JavaLinkedList {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        // Like a stack
        list.push("a");
        System.out.println(list.pop());

        // Like a queue
        list.offer("b");
        System.out.println(list.poll());

        // Like a list
        list.add("c");
        list.remove();

        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println(list.peekFirst()); // Returns value of first element, HEAD // c
        System.out.println(list.peekLast()); // Returns value of last element, TAIL // e

        list.addFirst("0");
        list.addLast("9");
        System.out.println(list.peekFirst()); // 0
        System.out.println(list.peekLast());  // 9

        System.out.println(list); // Looks like an array when printed

    }
}
