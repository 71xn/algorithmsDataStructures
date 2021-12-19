// Graphs in Java 16 - Finn Lestrange 2021

/*
* Adjacency Matrix -> 2D Array to store 1/0's to represent edges
* - Number of rows = the # of unique nodes
* - Number of columns = the # of unique nodes
*
* Complexity to check an edge: O(1) - constant time
* Space complexity: O(v^2)
*
* */

package tech.finnlestrange;

import java.util.ArrayList;

public class Graphs {
    public static void main(String[] args) {

        Graph graph = new Graph(5);

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
}

// creates the structure of the nodes
class Graph {
    ArrayList<Node> nodes;
    int[][] matrix;

    Graph(int size) {
        matrix = new int[size][size]; // space is O(v^2) -> the number of vertices in the matrix
        nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(int source, int destination) {
        matrix[source][destination] = 1; // add connection between source and destination node
    }

    public boolean checkEdge(int source, int destination) {
        return matrix[source][destination] == 1; // check if there is a connection between source node and destination node
    }

    public void print() {

        // print nodes as column headers
        System.out.print("  ");
        for (Node node : nodes) {
            System.out.print(node.data + " ");
        }
        System.out.println(); // new line

        // print adjacency matrix
        for (int i = 0; i < matrix.length; i++) { // rows
            System.out.print(nodes.get(i).data + " "); // row headers
            for (int j = 0; j < matrix.length; j++) { // cols
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(""); // print new line
        }
    }
}

// stores information in the nodes of the graph
class Node {
    char data;
    Node(char data) {
        this.data = data;
    }
}
