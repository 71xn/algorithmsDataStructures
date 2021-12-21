package tech.finnlestrange.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    protected ArrayList<Node> nodes;
    protected int[][] matrix; // protected so that anonymous functions can access them

    public Graph(int size) {
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


    // DFS CODE //
    public void depthFirstSearch(int src) {
        boolean[] visited = new boolean[this.matrix.length];
        dFSHelper(src, visited);
    }

    private void dFSHelper(int src, boolean[] visited) { // helper function for depth first search

        if (visited[src]) { // checking if source node has already been visited
            return;
        } else {
            visited[src] = true;
            System.out.println(nodes.get(src).getData() + " = visited"); // debug line to see visited nodes
        }

        for (int i = 0; i < matrix[src].length; i++) { // .length is the length of the sources row of adjacent nodes
            if (matrix[src][i] == 1) { // if 1, then there is an edge (connection) and it can be traveled to
                dFSHelper(i, visited); // call helper again with new source node to find next connection
            }
        }
    }

    // END OF DFS CODE //


    // START OF BFS CODE //

    public void breadthFirstSearch(int src) {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[this.matrix.length];

        queue.offer(src); // add starting node to search from to queue
        visited[src] = true;

        while (queue.size() != 0) {
            src = queue.poll();
            System.out.println(nodes.get(src).getData() + " = visited"); // debug line

            // iterate over rows of edges to look for adjacent neighbors on sources "same level"
            for (int i = 0; i < this.matrix[src].length; i++) {
                if (matrix[src][i] == 1 && !visited[i]) {
                    queue.offer(i); // add unvisited and connected node to the queue
                    visited[i] = true; // mark node as visited
                }
            }

        }
    }

    // END OF BFS CODE //


    public void print() {

        // print nodes as column headers
        System.out.print("  ");
        for (Node node : nodes) {
            System.out.print(node.getData()+ " ");
        }
        System.out.println(); // new line

        // print adjacency matrix
        for (int i = 0; i < matrix.length; i++) { // rows
            System.out.print(nodes.get(i).getData() + " "); // row headers
            for (int j = 0; j < matrix.length; j++) { // cols
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(""); // print new line
        }
    }



}
