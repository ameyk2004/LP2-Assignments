import java.util.*;

class Graph {
    private int numNodes;
    private int[][] adjMatrix;

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        this.adjMatrix = new int[numNodes][numNodes];
    }

    public void addEdge(int src, int dest) {
        adjMatrix[src][dest] = 1;
        adjMatrix[dest][src] = 1;
    }

    public void printGraph() {
        System.out.println("\nAdjacency List:");
        for (int i = 0; i < numNodes; i++) {
            System.out.print(i + " -> ");
            for (int j = 0; j < numNodes; j++) {
                if (adjMatrix[i][j] == 1) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[numNodes];
        List<Integer> result = new ArrayList<>();
        dfsHelper(start, visited, result);
        System.out.println("DFS Traversal: " + result);
    }

    private void dfsHelper(int node, boolean[] visited, List<Integer> result) {
        visited[node] = true;
        result.add(node);
        for (int i = 0; i < numNodes; i++) {
            if (adjMatrix[node][i] == 1 && !visited[i]) {
                dfsHelper(i, visited, result);
            }
        }
    }

    public void dfsIterative(int start) {
        boolean[] visited = new boolean[numNodes];
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                result.add(node);
                for (int i = numNodes - 1; i >= 0; i--) {
                    if (adjMatrix[node][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }

        System.out.println("DFS Iterative Traversal: " + result);
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[numNodes];
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);

        System.out.println("BFS Traversal (Level-wise):");
        while (!q.isEmpty()) {
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                int node = q.poll();
                System.out.print(node + " ");
                for (int j = 0; j < numNodes; j++) {
                    if (adjMatrix[node][j] == 1 && !visited[j]) {
                        visited[j] = true;
                        q.offer(j);
                    }
                }
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        Graph graph = new Graph(n);

        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();

        System.out.println("Enter edges in format: <src> <dest>");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.addEdge(u, v);
        }

        graph.printGraph();

        System.out.print("Enter start node for DFS: ");
        graph.dfs(sc.nextInt());

        System.out.print("Enter start node for Iterative DFS: ");
        graph.dfsIterative(sc.nextInt());

        System.out.print("Enter start node for BFS: ");
        graph.bfs(sc.nextInt());
    }
}
