package org.ameyTech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static class Node implements Comparable<Node>{
        int vertex;
        int weight;

        public Node(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString(){
            return "("+vertex+")";
        }
    }

    static  class Edge{
        int to;
        int weight;

        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString(){
            return "( " + to + ":" + weight +" )";
        }
    }

    static class Graph{
        int numNodes;
        ArrayList<ArrayList<Edge>> adjList;

        public Graph(int numNodes){
            this.numNodes = numNodes;
            this.adjList = new ArrayList<>();

            for (int i=0; i<numNodes; i++){
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v, int weight){
            adjList.get(u).add(new Edge(v, weight));
            adjList.get(v).add(new Edge(u, weight));
        }

        public void printGraph(){
            System.out.println("Graph Representation:");
            for (int i = 0; i < 5; i++) {
                System.out.print(i + " -> ");
                for (Edge e : adjList.get(i)) {
                    System.out.print(e + " ");
                }
                System.out.println();
            }

        }

        public void dijikstra(int source){

            int[] dist = new int[numNodes];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[source] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(source, 0));

            while (!pq.isEmpty()){
                Node node = pq.poll();
                int u = node.vertex;
                int distanceFromSource = node.weight;

                for(Edge edge: adjList.get(u)){
                    int v = edge.to;
                    int weight = edge.weight;
                    int newDist = distanceFromSource + weight;

                    if(newDist < dist[v]){
                        dist[v] = newDist;
                        pq.offer(new Node(v, newDist));
                    }
                }
            }

            System.out.println("\nDijkstra's Shortest Path from Source: " + source);
            for(int i = 0; i < numNodes; i++){
                System.out.println(source + " -> " + i + ": " + dist[i]);
            }
            System.out.println("=== END ===");

        }

        public void primsMST(int start){
            boolean[] visited = new boolean[numNodes];
            int[] parent = new int[numNodes];       // To store MST
            int[] key = new int[numNodes];          // Weight to reach node
            Arrays.fill(key, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(start, 0));
            key[start] = 0;

            while (!pq.isEmpty()){
                Node node = pq.poll();
                int u = node.vertex;

                if (visited[u]) continue;
                visited[u] = true;

                for (Edge edge : adjList.get(u)){
                    int v = edge.to;
                    int weight = edge.weight;

                    if (!visited[v] && weight < key[v]){
                        key[v] = weight;
                        parent[v] = u;
                        pq.offer(new Node(v, weight));
                    }
                }
            }

            System.out.println("\nPrim's MST Edges:");
            int totalWeight = 0;
            for (int i = 0; i < numNodes; i++) {
                if (parent[i] != -1) {
                    System.out.println(parent[i] + " - " + i + " (Weight: " + key[i] + ")");
                    totalWeight += key[i];
                }
            }
            System.out.println("Prim's MST Total Weight: " + totalWeight);
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(6);  // Since we have A-F = 6 nodes

        g.addEdge(0, 1, 4);   // A-B
        g.addEdge(0, 2, 5);   // A-C
        g.addEdge(1, 2, 11);  // B-C
        g.addEdge(1, 3, 9);   // B-D
        g.addEdge(2, 4, 3);   // C-E
        g.addEdge(3, 4, 13);  // D-E
        g.addEdge(3, 5, 2);   // D-F
        g.addEdge(4, 5, 6);   // E-F
        g.addEdge(1, 4, 7);

        g.printGraph();

        g.dijikstra(0);

        g.primsMST(0);


    }
}