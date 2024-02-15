# DSA-project
Virtual UAV itinerary program and simulation.
The program generates a path for UAV drones to fly to and collect data from. The path is dependent on previously collected data of the location, which is fed into an algorithm to provide the shortest route between locations with the greatest fire risk. The risk is calculated from the temperature (in Celsius), wind speeds, and relative humidity of the location.

## Table of Contents

- [DSA-project](#DSA-Project)
  - [Table of Contents](##table-of-contents)
  - [Usage](##usage)

## Usage
Compile all java files using JDK 8, then enter "java UnitTestTask7" into the terminal. A graph should be created from the text files "location.txt" and "UAVdata.txt". Then a path for each UAV will be created, the most optimal path for a UAV for a given cycle will be printed to terminal.
