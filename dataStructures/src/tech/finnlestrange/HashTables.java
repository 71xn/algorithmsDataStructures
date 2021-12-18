// Hash Tables / HashMaps in Java 16 - Finn Lestrange 2021

/*
*
* Dynamic data structures that stores a key-value pair
*
* Entry<Key, Value>
*
* */

package tech.finnlestrange;

import java.util.HashMap;
import java.util.Hashtable;

public class HashTables {
    public static void main(String[] args) {
        Hashtable<Integer, String> hashtable = new Hashtable<>(20);
        HashMap<Integer, String> hashMap = new HashMap<>(); // This is a non-thread safe, iterable version of HashTable

        hashtable.put(100, "bob");
        hashtable.put(101, "alice");
        hashtable.put(102, "john");
        hashtable.put(103, "dave");
        hashtable.put(104, "steve");

        for(Integer key : hashtable.keySet()) { // iterate over the set of keys
            System.out.println(key.hashCode() + "\t" + key + "\t" + hashtable.get(key));
        }

        // Index of element is calculated using hash % capacity

        for(Integer key : hashtable.keySet()) { // iterate over the set of keys
            System.out.println(key.hashCode() % 20 + "\t" + key + "\t" + hashtable.get(key));
        }

        /*
          4	104	steve
          3	103	dave
          2	102	john
          1	101	alice
          0	100	bob
        * */


    }
}
