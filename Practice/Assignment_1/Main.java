import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static class GraphTraversal{
        int numNodes;
        int[][] adjList;

        public GraphTraversal(int numNodes){
            this.numNodes = numNodes;
            this.adjList = new int[numNodes][numNodes];
        }

        public void addEdge(int u, int v){
            adjList[u][v] = 1;
            adjList[v][u] = 1;
        }

        public void printGraph(){
            for(int i=0; i<numNodes; i++){
                System.out.print(i+" -> ");
                for(int j=0; j<numNodes; j++){
                    if(adjList[i][j] == 1)
                        System.out.print(j+ " ");
                }
                System.out.println();
            }
        }

        public void dfs(int start){
            //dfs helper
            System.out.println("\n\nDFS");
            boolean[] visited = new boolean[numNodes];
            visited[start] = true;
            List<Integer> result = new ArrayList<>();
            dfsHelper(start, visited, result);
            System.out.println(result);
        }

        private void dfsHelper(int node, boolean[] visited, List<Integer> result){
            visited[node] = true;
            result.add(node);

            for (int i=0;i<numNodes;i++){
                if(!visited[i] && adjList[node][i] == 1){
                    dfsHelper(i, visited, result);
                }
            }
        }

        public void bfs(int start){
            System.out.println("\n\nBFS");
            Queue<Integer> queue = new LinkedList<>();
            List<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[numNodes];
            visited[start] = true;
            queue.offer(start);


            while (!queue.isEmpty()){
                int levelSize = queue.size();
                for (int k = 0; k<levelSize; k++) {
                    int node = queue.poll();
                    visited[node] = true;
                    System.out.print(node+" ");
                    result.add(node);

                    for (int i = 0; i < numNodes; i++) {
                        if (!visited[i] && adjList[node][i] == 1) {
                            queue.offer(i);
                            visited[i] = true;
                        }
                    }
                }
                System.out.println();
            }

            System.out.println(result);
        }

        public void dfsPath(int start, int target){
            System.out.println("\n\nDFS Path : "+start+" -> "+target);

            boolean[] visited = new boolean[numNodes];
            List<Integer> result = new ArrayList<>();

            boolean found = dfsPathHelper(start, visited, result, target);
            if (found) {
                System.out.println("Path found: " + result);
            } else {
                System.out.println("No path found from " + start + " to " + target);
            }
            System.out.println(result);
        }

        private boolean dfsPathHelper(int node, boolean[] visited, List<Integer> result, int target){
            visited[node] = true;
            result.add(node);

            if(node == target) return true;

            for (int i=0;i<numNodes;i++){
                if(!visited[i] && adjList[node][i] == 1){
                    if (dfsPathHelper(i, visited, result, target)) {
                        return true;
                    }
                }
            }

            return false;
        }

        public void bfsPath(int start, int target){

        }
    }

    public static void main(String[] args) {

        GraphTraversal graph = new GraphTraversal(5);

        // Hardcoded edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(2, 4);


        graph.printGraph();
        graph.dfs(4);
        graph.bfs(0);
        graph.dfsPath(3, 2);
        graph.bfsPath(0, 4);
    }
}