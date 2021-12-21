// Breadth First Search in Java 16 - Finn Lestrange 2021

/*
* Breadth First Search
* --------------------
*
* Searching algo for traversing a tree or graph data structure.
* This is done by searching one level at a time rather than one branch at a time (like DFS)
*
* Here, we use a queue to traverse in order, rather than a stack like in DFS
*
* BFS better if destination node is, on average, closer to source node, if further away, then DFS is preferred
*
* */

// See tech.finnlestrange.graphs.Graph.java for code


package tech.finnlestrange;

// Importing graph packages
import tech.finnlestrange.graphs.Graph;
import tech.finnlestrange.graphs.Node;

public class BreadthFirstSearch {
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

        graph.breadthFirstSearch(0);
    }
}
