// Stack in Java 16

// Stack - First In, Last Out Data Structure

/*
Use's of stacks:

Calling functions e.g. stack frames
undo and redo features in editors

*/

package tech.finnlestrange;

import java.util.Stack;

public class JavaStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();

        // Check if stack is empty - returns boolean
        System.out.println(stack.isEmpty()); // True

        stack.push("hello");
        System.out.println(stack.isEmpty()); // False
        System.out.println(stack.pop());
    }
}
