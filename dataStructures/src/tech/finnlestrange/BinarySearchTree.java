// Binary Search Tree in Java 16 - Finn Lestrange 2021

/*
* A binary tree is a tree where each node has no more than two children
*
* A binary search tree is arranged in a way such that the left child is less than the parent, and the right node greater
* - The Smallest value will be the deepest left most child, and the deepest right most child will be the largest value
*
* Complexity to find value: O(log n) if balanced, else if unbalanced worst case is O(n)
*
* space complexity: O(n) -> n = number of nodes
*
* */

package tech.finnlestrange;

public class BinarySearchTree {

    class Node {
        protected int data;

        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }

    class BST {

        Node root;

        // Note: using helper methods for recursion

        // Inserting a node
        public void insert(Node node) {
            root = insertHelper(this.root, node);
        }

        private Node insertHelper(Node root, Node node) {
            int data = node.getData();

            if (root == null) {
                root = node;
                return root;
            } else if (data < root.getData()) { // if less than assign as left child
                root.left = insertHelper(root.left, node); // if no children, then node is root of subtree
            } else if (data > root.getData()) { // if greater than, assign as right child
                root.right = insertHelper(root.right, node);
            }

            return root; // return current root node
        }


        // Displaying the tree
        public void display() {
            displayHelper(this.root);
        }

        private void displayHelper(Node root) {
            if (root != null) { // making sure that tree is not empty
                // in-order traversal -> left, right, root
                displayHelper(root.left); // ascending order, start with smallest
                System.out.println(root.data);
                displayHelper(root.right);
            }
        }


        // Finding a node
        public boolean search(int data) {
            return searchHelper(this.root, data);
        }

        private boolean searchHelper(Node root, int data) {
            if (root == null) {
                return false;
            } else if (root.getData() == data) { // if current node is one we are looking for
                return true;
            } else if (root.getData() > data) { // go left if current node's data is greater than what we are looking for
                return searchHelper(root.left, data);
            } else {
                return searchHelper(root.right, data); // go right if current node's data is smaller than what we are looking for
            }
        }


        // Removing a Node
        public void remove(int data) {
            if (search(data)) {
                removeHelper(this.root, data);
            } else {
                System.out.println("data: " + data + ", could not be found");
            }
        }

        private Node removeHelper(Node root, int data) {
            if (root == null){
                return null;
            } else if (data < root.getData()) { // if data less, go left
                root.left = removeHelper(root.left, data);
            } else if (data > root.getData()){
                root.right = removeHelper(root.right, data); // if data greater, go right
            } else { // if we reach this, then we have found node that needs to be removed
                // check if leaf node (no children)
                if (root.left == null && root.right == null) {
                    root = null;
                } else if (root.right != null) { // if node to remove has a right child, we have to shift around other nodes to keep order
                    // find a success to replace this node
                    root.data = successor(root);
                    root.right = removeHelper(root.right, root.getData()); // remove right node after replacing
                } else { // find a predecessor to replace this node
                    root.data = predecessor(root);
                    root.left = removeHelper(root.left, root.getData());
                }
            }
            return root;
        }

        // Need to find new nodes to fill places when removing a node
        private int successor(Node root) {
            // find the least value below the right child of this root node
            root = root.right; // go right and then look as far left as possible
            while (root.left != null) {
                root = root.left;
            }
            return root.getData();
        }

        private int predecessor(Node root) {
            // find the greatest value below the left child of this root node
            root = root.left;
            while (root.right != null) {
                root = root.right;
            }
            return root.getData();
        }


    }

    BinarySearchTree() { // call from non-static constructor
        BST tree = new BST();
        tree.insert(new Node(5));
        tree.insert(new Node(1));
        tree.insert(new Node(9));
        tree.insert(new Node(2));
        tree.insert(new Node(7));

        tree.display();

        System.out.println(tree.search(9)); // true
        System.out.println(tree.search(11)); // false

        // remove 9

        tree.remove(9);
        System.out.println(tree.search(9)); // false -> removed node

    }


    public static void main(String[] args) {
        new BinarySearchTree();
    }

}
