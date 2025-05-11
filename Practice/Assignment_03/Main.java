package Practice.Assignment_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;

public class Main {

    static class Node implements Comparable<Node>{
        int vertex;
        int weight;

        public Node(int v, int w){
            this.vertex = v;
            this.weight = w;
        }

        @Override
        public int compareTo(Node o){
            return Double.compare(this.weight, o.weight);
        }

        @Override
        public String toString(){
            return "("+vertex+")";
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof Node){
                Node other = (Node) o;
                return this.vertex == other.vertex;
            }
            return false;
        }

        @Override
        public int hashCode(){
            return Objects.hash(vertex);
        }
    }

    static class Edge{
        int to;
        int weight;

        public Edge(int to, int w){
            this.to = to;
            this.weight = w;

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
            adjList = new ArrayList<>();
            for (int i=0; i<numNodes; i++){
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v, int w){
            adjList.get(u).add(new Edge(v,w));
            adjList.get(v).add(new Edge(u,w));
        }

        public void printGraph(){

            System.out.println("\n\nGraph");
            for (int i=0; i<numNodes; i++){
                System.out.print(i+" -> ");
                for (Edge edge : adjList.get(i)){
                    System.out.print(edge+" ");
                }
                System.out.println();
            }
        }
        //djikstra
        public void dijikstra(int src){
            PriorityQueue<Node> pq = new PriorityQueue<>();
            int[] dist = new int[numNodes];
            Arrays.fill(dist, Integer.MAX_VALUE);

            dist[src] = 0;
            pq.offer(new Node(0, dist[src]));

            while (!pq.isEmpty()){
                Node node = pq.poll();
                int u = node.vertex;
                int distanceFromSource = node.weight;

                for (Edge edge: adjList.get(u)){
                    int v = edge.to;
                    int weight = edge.weight;
                    int newDist = distanceFromSource + weight;

                    if(newDist < dist[v]){
                        dist[v] = newDist;
                        pq.offer(new Node(v, dist[v]));
                    }
                }

            }

            System.out.println("\nDijkstra's Shortest Path from Source: " + src);
            for(int i = 0; i < numNodes; i++){
                System.out.println(src + " -> " + i + ": " + dist[i]);
            }
            System.out.println("=== END ===");
        }

        public void primsMST(int start){
            boolean[] visited = new boolean[numNodes];
            int[] key = new int[numNodes];
            int[] parent =  new int[numNodes];

            Arrays.fill(key, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);
            Arrays.fill(visited, false);

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(start, 0));

            while (!pq.isEmpty()){
                Node node = pq.poll();
                int u = node.vertex;

                if(visited[u]) continue;
                visited[u] = true;

                for (Edge edge : adjList.get(u)){
                    int v = edge.to;
                    int weight = edge.weight;

                    if(!visited[v] && key[v] > weight){
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



    // prims

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