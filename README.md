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


Test harnesses for Graph, hash table and heap has been implemented, but do use with discretion.

### Linked List


Used for implementation of GraphMap to dynamically store node and edge objects.
### GraphMap

### Heap

### Hash Table


## Algorithms

Dijkstra's algorithm, breadth first search and depth first search have all been implemented within GraphMap :>
