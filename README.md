# Graph-Algorithms-Visualizer

### Learning Outcomes

- How to draw graphs, create vertices and connect them with edges, and handle events with swing
- Implementing multiple algorithms, like Breadth-First Search, Depth-First Search, Dijkstra's, and Prim's Algorithms
- Making the application extensible to support more algorithms in the future

### About

This project builds a Java GUI application with Swing to create graphs and visualize graph algorithms like traversals, 
spanning trees, and shortest pathfindings.

The user can add and remove vertices and edges by switching between the different modes present in the Mode menu item. 
After a graph has been drawn by the user, one of four algorithms can be chosen from the Algorithms menu item to run on the 
created graph.

The algorithm results for each algorithm is displayed as follows:

- For Breadth-First Search: BFS : <The traversal separated by "->">. Example: BFS : A -> B -> C -> D -> E -> F
- For Depth-First Search: DFS : <The traversal separated by "->">. Example: DFS : B -> C -> A -> D -> F -> E
- For Dijkstra's: A list of <Vertex>=<Cost> pairs separated by a comma. Example: A=1, B=2, C=3, D=4, which means 
that the shortest paths of A, B, C, D from the selected source vertex are 1, 2, 3, 4 respectively.
- For Prim's: A list of the <Child>=<Parent> pairs separated by a comma. Example: B=A, C=B, D=A, which means 
that the parents of B, C, and D in the minimum spanning tree are A, B, and A respectively.

# General Info

To learn more about this project, please visit [HyperSkill Website - Graph-Algorithms Visualizer](https://hyperskill.org/projects/207).

This project's difficulty has been labelled as __Challenging__ where this is how
HyperSkill describes each of its four available difficulty levels:

- __Easy Projects__ - if you're just starting
- __Medium Projects__ - to build upon the basics
- __Hard Projects__ - to practice all the basic concepts and learn new ones
- __Challenging Projects__ - to perfect your knowledge with challenging tasks

This repository contains

    visualizer package
        - Contains the ApplicationRunner java class that contains the main method to run the project by calling an instance of MainFrame class
        - Contains the MainFrame java class that sets up the ain JFrame of the program
        - Contains the Graph java class that sets up the JPanel that acts as a container of the main components of the program
        - Contains the Vertex java class whose objects are JPanels that represent the nodes of the visualized graph
        - Contains the Edge java class whose objects are JComponents that represent the edges of the visualized graph
        - Contains the Mode java enum that contains the different modes of adding and deleting vertices and edges
        - Contains the Algorithm enum that contains the different algorithms that can be applied to the drawn graph

    algorithms package
        - Contains the AlgorithmSetter java class that acts as the Context class of the Strategy design pattern
        - Contains the GraphAlgorithm java interface that acts as the Strategy interface for all implemented algorithms
        - Contains the DFSAlgorithm java class that implements the Strategy interface and implements the Depth-First Search traversal algorithm
        - Contains the BFSAlgorithm java class that implements the Strategy interface and implements the Breadth-First Search traversal algorithm
        - Contains the DijkstrasAlgorithm java class that implements the Strategy interface and implements Dijkstra's shortest path algorithm
        - Contains the PrimsAlgorithm java class that implements the Strategy interface and implements Prim's minimum-spanning tree algorithm

Project was built using java 21

# How to Run

Download the visualizer and algorithms repositories to your machine. Create a new project in IntelliJ IDEA, then move the downloaded
repositories to the src folder and run the ApplicationRunner java class.
