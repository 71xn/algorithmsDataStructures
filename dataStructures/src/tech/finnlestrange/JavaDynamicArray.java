// Dynamic Array Java 16 - Finn Lestrange 2021

/* Dynamic Array Info

List data structure with a resizeable capacity

also known as ArrayList in Java, or List in Python

Static array has as fixed capacity new String[size], search O(n)
Dynamic array does not have a fixed capacity

* */

package tech.finnlestrange;

import java.util.ArrayList;

public class JavaDynamicArray {

    // DIY Dynamic Array Class
    class DynamicArray {

        // Set to private so that instance variables cannot be directly referenced outside the class
        private int size; // current size of dynamic array
        private int capacity = 10; // initial size of dynamic array
        private Object[] array; // The actual array of Objects

        // Constructor - runs when new instance of class is created
        public DynamicArray() {
            this.array = new Object[this.capacity];
        }

        // This is run if the user specified their own initial capacity for the Dynamic Array
        public DynamicArray(int capacity) {
            this.array = new Object[capacity];
        }

        // Adds elements to the dynamic array
        public void add(Object data) {
            if (size >= capacity) {
                grow();
            }
            array[size] = data;
            size++;
        }

        // Inserts an element at a given postition
        public void insert(int index, Object data) {
            // If we have reached the capacity, increase the size of dynamic array
            if (size >= capacity) {
                grow();
            }

            // Shift to right to make room for the insertion
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            // Set data to new data after shift
            array[index] = data;

            // Increase size
            size++;

        }

        public void delete(Object data) {
            for (int i = 0; i < size; i++) {
                if (array[i] == data) {
                    // If data matches at current index, shift to left by one place
                    for (int j = 0; j < (size - i - 1); j++) {
                        array[i + j] = array[i + j + 1]; // Set current to next element
                    }
                    array[size - 1] = null;
                    size--;

                    if (size <= (int) (capacity / 3)) {
                        shrink(); // Return to this later
                    }
                }
            }
        }

        public int search(Object data) {
            for (int i = 0; i < size; i++) {
                if (array[i] == data) {
                    return i;
                } else {
                    continue;
                }
            }
            return -1;
        }

        // Expands the size of the array
        private void grow() {
            int newCapacity = (int) (capacity * 2);
            Object[] newArray = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            capacity = newCapacity;
            array = newArray;
        }

        // Shrinks the size of the array
        private void shrink() {

            // Shrink size by a factor of 2
            int newCapacity = (int) (capacity / 2);
            Object[] newArray = new Object[newCapacity];

            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            capacity = newCapacity;
            array = newArray;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int length() {
            return size;
        }


        // Returns string of array
        public String toString() {
            String string = "[";
            if (size == 0) {
                return "[]";
            } else {
                for (int i = 0; i < size; i++) {
                    // adds terminating characters to string
                    if (i == size - 1) {
                        string += array[i] + "]";
                    } else {
                        string += array[i] + ", ";
                    }
                }
                return string;
            }
        }


        // Getters for debug

        // Capacity getter
        public int getCapacity() {
            return capacity;
        }

        // Size getter
        public int getSize() {
            return size;
        }
    }

    // Use non-static method to reference non-static local class
    public JavaDynamicArray() {
        ArrayList<String> arrayList = new ArrayList<>(); // Declaring a new string arraylist

        // Declaration of instance of diy class
        DynamicArray dynamicArray = new DynamicArray();
        // System.out.println(dynamicArray.getCapacity());

        dynamicArray.add("a");
        dynamicArray.add("b");
        dynamicArray.add("c");
        System.out.println(dynamicArray);
        System.out.println("is empty? " + dynamicArray.isEmpty());
        System.out.println("length of array: " + dynamicArray.length());

        dynamicArray.insert(1, "wow");
        System.out.println(dynamicArray);

        dynamicArray.delete("b");
        System.out.println(dynamicArray);

        System.out.println(dynamicArray.search("c")); // 2 -> found at index 2
        System.out.println(dynamicArray.search("y")); // -1 -> not found

    }

    public static void main(String[] args) {
        new JavaDynamicArray();
    }
}


