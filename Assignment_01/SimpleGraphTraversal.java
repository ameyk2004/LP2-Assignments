package org.ameyTech;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SimpleGraphTraversal {

    int numNodes;
    int[][] adjMatrix;

    public GraphTraversal(int numNodes){
        this.numNodes = numNodes;
        this.adjMatrix = new int[numNodes][numNodes];
    }

    public void addEdge(int u, int v){

        if(u < 0 || v <0 || u > numNodes || v > numNodes){
            System.out.println("Invalid Edge Creation Request");
        }
        else{
            // since undirected graph
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        }
    }

    public void printGraph(){
        System.out.println("\n\n------- Graph -------");
        for(int i=0; i<numNodes; i++){
            System.out.print(i+" -> ");
            for(int j=0; j<numNodes; j++){
                if(adjMatrix[i][j] == 1)
                    System.out.print(j+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    public void dfs(int start){
        System.out.println("\n\n------- DFS -------");

        boolean[] visited = new boolean[numNodes];
        List<Integer> result = new ArrayList<>();
        dfsHelper(start, visited, result);
        System.out.println(result);
        System.out.println("--------------------");
    }

    public void dfsHelper(int node, boolean[] visited, List<Integer> result){
        visited[node] = true;
        result.add(node);

        for (int i=0; i<numNodes; i++){
            if(adjMatrix[node][i] == 1 && !visited[i]){
                dfsHelper(i, visited, result);
            }
        }
    }

    public void bfs(int start){
        System.out.println("\n\n------- BFS -------");
        boolean[] visited = new boolean[numNodes];
        Queue<Integer> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        visited[start] = true;
        q.offer(start);

        while (!q.isEmpty()){

            int levelSize = q.size();

            for (int m=0; m<levelSize; m++){
                int node = q.poll();
                result.add(node);
                System.out.print(node + " ");

                for (int i=0; i<numNodes; i++){
                    if(adjMatrix[node][i] == 1 && !visited[i]){
                        q.offer(i);
                        visited[i] = true;
                    }
                }
            }

            System.out.println();
        }
        System.out.println(result);
        System.out.println("--------------------");
    }

    public void dfsPath(int start, int dest){

    }

    public void bfsPath(int start, int dest){

    }



    public static void main(String[] args) {

        SimpleGraphTraversal graph = new SimpleGraphTraversal(5);

        // Hardcoded edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(2, 4);


        graph.printGraph();
        graph.dfs(3);
        graph.bfs(0);
        graph.dfsPath(0, 4);
        graph.dfsPath(0, 4);

    }

}
