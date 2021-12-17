// Recursion in Java 16 - Finn Lestrange 2021

/*
* Recursion is a method that calls itself, it is the repetition of a procedure, whereas iteration is repetition of a process
*
* Used in sorting algorithms and navigating trees
*
* Can be slower and use more memory depending on complexity
*
* Divide a problem into a subtype of the original
*
* */

package tech.finnlestrange;

public class Recursion {

    private static int power(int x, int y) {
        if (y == 0) { // Base case
            return 1;
        } else { // Recursive case
            return x * power(x, y - 1);
        }
    }

    private static int factorial(int n) {
        if (n == 1) { // Base case
            return 1;
        } else { // Recursive Case
            return n * factorial(n - 1);
        }
    }

    // Recursive Procedure
    private static void walkRec(int steps) {
        if (steps == 1) { // Base case
            System.out.println("you took a step");
        } else { // Recursive case
            System.out.println("you took a step");
            walkRec(steps - 1);
        }
    }

    // Iterative procedure
    private static void walk(int steps) {
        for (int i = 0; i < steps; i++) {
            System.out.println("you took a step");
        }
    }

    public static void main(String[] args) {

        // Iterative
        System.out.println("iterative algorithm");
        walk(5);

        System.out.println("");
        // Recursive
        System.out.println("recursive algorithm");
        walkRec(5);

        System.out.println();
        System.out.println("factorial 7: " + factorial(7));

        System.out.println();
        System.out.println(power(2, 8));
    }
}
