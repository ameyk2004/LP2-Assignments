package org.ameyTech;

import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int x, y;
        double g, f, h;
        Node parent;

        Node(int x, int y){
            this.x = x;
            this.y = y;

            this.g = Double.MAX_VALUE;
            this.h = 0;
            this.f = g+h;
            this.parent = null;
        }

        public void setGandH(double g, double h){
            this.g = g;
            this.h = h;
            this.f = g+h;
        }


        @Override
        public int compareTo(Node o) {
            return Double.compare(this.f, o.f);
        }

        @Override
        public int hashCode(){
            return Objects.hash(x,y);
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof Node){
                Node other = (Node) o;
                return ((this.x == other.x ) && (this.y == other.y ));
            }

            return false;
        }

        @Override
        public String toString() {
            return "("+x+","+y+")";
        }
    }

    static class AstarAlgorithm{
        int[][] maze;
        int MAZE_ROWS;
        int MAZE_COLS;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        public AstarAlgorithm(int[][] maze){
            this.maze = maze;
            this.MAZE_ROWS = maze.length;
            this.MAZE_COLS = maze[0].length;
        }

        public void findPath(Node start, Node target){
            PriorityQueue<Node> openList = new PriorityQueue<>();
            Set<Node> closedList = new HashSet<>();

            start.setGandH(0, heuristic(start, target));
            openList.offer(start);

            System.out.println("Start Node Added");

            while (!openList.isEmpty()){
                Node curr = openList.poll();

                if(curr.equals(target)){
                    printPath(curr);
                    return;
                }

                // explore neighbours
                for (int i=0; i<4; i++){

                    int nx = curr.x + dx[i];
                    int ny = curr.y + dy[i];

                    if(!isValid(nx, ny)) continue;

                    Node neighbour = new Node(nx, ny);
                    neighbour.setGandH(curr.g + 1, heuristic(neighbour, target));
                    neighbour.parent = curr;
                    openList.add(neighbour);
                }


            }
            System.out.println("No Path Found");
        }

        private int heuristic(Node a, Node b){
            return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
        }

        private void printPath(Node target){

            Node node = target;
            List<Node> path = new ArrayList<>();

            while (node !=null){
                path.add(node);
                node = node.parent;
            }

            Collections.reverse(path);

            System.out.println(path);

        }

        private boolean isValid(int x, int y) {
            return (x >= 0 && y >= 0 && x < MAZE_ROWS && y < MAZE_COLS && maze[x][y] == 0);
        }

    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0}
        };

        Node start = new Node(0, 0);
        Node end = new Node(4, 4);

        AstarAlgorithm astar = new AstarAlgorithm(maze);
        astar.findPath(start, end);
    }
}