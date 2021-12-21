// Depth First Search in Java 16 - Finn Lestrange 2021

/*
* Depth first search: DFS -> a search algorithm for traversing a tree or graph data structure
* - Pick a route
* - Keep going till you reach the end or a previously visited node, e.g. go as deep as you can down one route
* - Once at end, backtrack to last node that has unvisited adjacent neighbors
*
* */

// See tech.finnlestrange.graphs.Graphs.java for code

package tech.finnlestrange;

import tech.finnlestrange.graphs.Graph;
import tech.finnlestrange.graphs.Node;

public class DepthFirstSearch {
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

        graph.depthFirstSearch(0);
    }
}
