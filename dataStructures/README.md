# ☕ Common Data Structures & Algorithms Implemented in Java 16

---

## Definitions

**Data Structure** - `A named location that can be used to store and organize data`

**Algorithm** - `A collection of steps to solve a problem`


## Big O Notation

**BigO Notation** - `How code slows as data grows.`

1. Describes the performance of an algorithm as the amount of data increases
2. Machine independent (# of steps to completion)
3. Ignore smaller operations (O(n + 1) -> O(n)

*Examples:*
```
n = "ammount of data";

O(1) = performance stays the same as data increases

O(n) = performance scales linearly as with data size change e.g. Linear search (single for loop)

O(log n) = performace scales logarithmicly with data size change

O(n^2) = performance scales exponentially with power 2 
```
\
O(n)
```java
class Example {
    public int addUp(int n) {
        int sum = 0;
        for (int i = 0; i<= n; i++) {
            sum += i;
        }
    }
}
```
\
O(1)
```java
class Example {
    public int addUp(int n) {
        int sum = n * (n + 1) / 2; 
        return sum;
    }
}
```
\
**Examples for common Big O**

- `O(1)` = constant time
  - random access of array element
  - inserting at beginning of list
- `O(log n)` = logarithmic time
  - binary search
- `O(n)` = linear time
  - looping through array elements
  - single for loop
- `O(n log n )` = quasilinear time
  - quicksort
  - mergesort
  - heapsort
- `O(n^2)` = quadratic time
  - insertion sort
  - selection sort
  - bubblesort
- `O(!n)` = factorial time
  - TSP

