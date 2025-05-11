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