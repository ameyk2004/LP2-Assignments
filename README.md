# LP2-Assignments

## Assignment 1 

### **Graph Representation and Traversal**

1. **Graph Class**:

    * **Attributes**:

        * `numNodes`: Number of nodes.
        * `adjMatrix`: 2D array representing edges (1 for edge, 0 for no edge). For undirected graphs, `adjMatrix[u][v] = adjMatrix[v][u] = 1`.
    * **Methods**:

        * `addEdge(u, v)`: Adds an edge between nodes `u` and `v`.
        * `printGraph()`: Prints the adjacency matrix.

---

### **DFS (Depth-First Search)**

* **`dfsRecursive(start)`**: Initiates DFS from the `start` node, marking nodes as visited.

* **`dfsHelper(node, visited, result)`**: Recursively visits unvisited neighbors of a node.

* **Visited Nodes**: A `visited` array is used to track which nodes have been processed.

---

### **BFS (Breadth-First Search)**

* **`bfs(start)`**: Uses a queue to explore nodes level by level starting from `start`.
* **Queue**: Nodes are processed in the order they are discovered. A `visited` array ensures nodes are not reprocessed.

---

## Assignmnent 2 : Astar Algo

#### **Key Components**:

1. **Node Class**:

   * **Attributes**:

      * `x`, `y`: Coordinates of the node.
      * `g`, `h`, `f`: Cost values for A\* (g: path cost, h: heuristic, f = g + h).
      * `parent`: Points to the parent node, helping to trace the path back.
   * **Methods**:

      * `setGandH(g, h)`: Sets the g, h, and f values of a node.
      * `compareTo(Node o)`: Compares nodes based on their f value for priority queue sorting.
      * `hashCode()` and `equals()`: Used for checking node equality and ensuring proper hashing in sets.
      * `toString()`: String representation of a node for easy output.

2. **AstarAlgorithm Class**:

   * **Attributes**:

      * `maze`: 2D array representing the maze where `0` is an open space and `1` is a wall.
      * `MAZE_ROWS`, `MAZE_COLS`: Dimensions of the maze.
      * `dx`, `dy`: Direction arrays for moving up, down, left, and right.
   * **Methods**:

      * `findPath(Node start, Node target)`: Practice.Assignment_1.Main method for A\* algorithm. It initializes the start node, adds it to a priority queue (open list), and processes nodes until the target is found.
      * `heuristic(Node a, Node b)`: Calculates the Manhattan distance between two nodes as a heuristic for A\*.
      * `printPath(Node target)`: Traces and prints the path from the target node back to the start node using the parent pointers.
      * `isValid(int x, int y)`: Checks if a position is within maze bounds and not a wall.

---

### **Summary**:

* **A* Search*\*: The algorithm finds the shortest path from a start node to a target node by using a priority queue. It considers both the cost to reach a node (`g`) and an estimate of the cost to reach the target (`h`).

* **Node**: Each node stores its position, cost values (`g`, `h`, `f`), and parent for path reconstruction.

* **Algorithm Flow**: The algorithm begins at the start node, explores neighboring nodes, and selects the next node with the lowest `f` value until it reaches the target.

