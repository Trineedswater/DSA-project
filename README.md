# UAV-itinerary

Virtual UAV itinerary program and simulation.
The program generates a path for UAV drones to fly to and collect data from. The path is dependent on previously collected data of the location, which is fed into an algorithm to provide the shortest route between locations with the greatest fire risk. The risk is calculated from the temperature (in Celsius), wind speeds, and relative humidity of the location.
This short program was created to demonstrate java implementation of various data structures and algorithms.

## Table of Contents

- [UAV-itinerary](#uav-itinerary)
  - [Table of Contents](#table-of-contents)
  - [Usage](#usage)
  - [Data structures](#data-structures)
    - [Linked List](#linked-list)
    - [GraphMap](#graphmap)
    - [Heap](#heap)
    - [Hash Table](#hash-table)
  - [Algorithms](#algorithms)

## Usage

Compile all java files using JDK 8, then enter "java main" into the terminal. 

```bash
javac *.java
java main
```

A graph should be created from the text files "location.txt" and "UAVdata.txt". Then a path for each UAV will be created, the most optimal path for a UAV for a given cycle will be printed to terminal.
Then follow the menu instructions to modify the location graph and other tasks.

## Data structures

This program relies on use of simple dynamic data structures. Test harnesses for Graph, hash table and heap has been implemented, but do use with discretion.

### Linked List

Implemented using Node objects which store Java Object data types and another node object.
Linked List class itself stores the head and tail Nodes as well as the size of the list.

Used for implementation of GraphMap to dynamically store node and edge objects.

### GraphMap

Implemented as as an adjacency list, where a Linked List of Graph Node objects are stored, with each object storing itself a Java Object data type as well as a list of edges it is adjacent to. Another list is used to store the edges of the map, which similarly contain a Java object to store the weight.
The graph has breadth and depth first search algorithms as well as Dijkstra's algorithm for graph traversal. The map can also be displayed as an adjacency list or matrix.

### Heap

Implemented as a an array of Heap Entry objects, it will copy to an array of a smaller / larger size when the size of the Heap passes a threshold. Heap Entry data type stores an Object data type and a priority for the heap.
The heap uses trickle up and trickle down algorithms for its remove and insert functions. Although a heap sort has been implemented to sort arrays of integers, it will not guarantee a fully sorted array.

### Hash Table

Implemented as an array of Hash Entries. The hash algorithm relies on a string for the key, where a short maths function is used at each character in the key to produce a hash result. The dynamic resizing of the array relies on an algorithm to find the next prime value for the length of the array, and then copies all the hash entries over to the new array; a load factor is used to initiate this algorithm.
