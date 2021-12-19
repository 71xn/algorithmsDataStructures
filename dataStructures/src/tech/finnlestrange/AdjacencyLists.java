// Adjacency Lists in Java 16 - Finn Lestrange 2021

/*
* Adjacency Lists -> an array/arraylist of LinkedLists
* - Each LinkedList has a unique node at the head
* - All adjacent neighbors to that node are added to that node's LinkedList
*
* Runtime complexity (to check an edge): O(v) , v = vertices (nodes)
* Space complexity: O(v + e)
*
* */

package tech.finnlestrange;


import java.util.ArrayList;
import java.util.LinkedList;

public class AdjacencyLists {

    class Graph {

        ArrayList<LinkedList<Node>> alist;

        Graph() {
            alist = new ArrayList<>();
        }

        public void addNode(Node node) {
            LinkedList<Node> currentList = new LinkedList<>();
            currentList.add(node);
            alist.add(currentList);
        }

        public void addEdge(int src, int dst) {
            LinkedList<Node> currentList = alist.get(src); // get linked list of source node and add destination node to it
            Node dstNode = alist.get(dst).get(0); // address of node we want to link to
            currentList.add(dstNode);
            alist.set(src, currentList);
        }

        public boolean checkEdge(int src, int dst) {
            LinkedList<Node> currentList = alist.get(src);
            Node dstNode = alist.get(dst).get(0); // address of node we want to link to
            return currentList.contains(dstNode);
        }

        public void print() {
            for (LinkedList<Node> list : alist) {
                for (Node node : list) {
                    System.out.print(node.data);
                }
                System.out.println(""); // new line
            }
        }

    }

    class Node {
        char data;
        Node(char data) {
            this.data = data;
        }
    }

    AdjacencyLists() {
        Graph graph = new Graph();

        graph.addNode(new Node('A'));
        graph.addNode(new Node('B'));
        graph.addNode(new Node('C'));
        graph.addNode(new Node('D'));
        graph.addNode(new Node('E'));

        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(4,0);

        graph.print();

        System.out.println("");
        System.out.println(graph.checkEdge(0,1)); // true
        System.out.println(graph.checkEdge(2,0)); // false
        System.out.println(graph.checkEdge(3,4)); // true


    }

    public static void main(String[] args) {new AdjacencyLists();}
}
