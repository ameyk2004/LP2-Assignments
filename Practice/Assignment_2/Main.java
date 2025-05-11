package Practice.Assignment_2;

import java.util.*;

public class Main {

    static class Node implements Comparable<org.ameyTech.Main.Node>{
        int x,y;
        double g,h,f;
        org.ameyTech.Main.Node parent;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
            this.g = Double.MAX_VALUE;
            this.h = 0;
            this.f = g +h;
            parent = null;
        }

        public void setGandH(double g, double h){
            this.g = g;
            this.h = h;
            this.f = g + h;
        }

        @Override
        public int compareTo(org.ameyTech.Main.Node o) {
            return Double.compare(this.f, o.f);
        }

        @Override
        public boolean equals(Object o){
            if (o instanceof org.ameyTech.Main.Node){
                org.ameyTech.Main.Node other = (org.ameyTech.Main.Node) o;
                return (other.x == this.x && other.y == this.y);
            }
            return false;
        }

        @Override
        public int hashCode(){
            return Objects.hash(x,y);
        }

        @Override
        public String toString(){
            return "("+x+","+y+")";
        }
    }

    static class AstarAlgorithm{
        int[][] maze;
        int ROWS_MAX;
        int COLS_MAX;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0,-1, 1};

        public AstarAlgorithm(int[][] maze){
            this.maze = maze;
            this.ROWS_MAX = maze.length;
            this.COLS_MAX = maze[0].length;
        }

        public void findPath(org.ameyTech.Main.Node start, org.ameyTech.Main.Node target){
            PriorityQueue<org.ameyTech.Main.Node> openList = new PriorityQueue<>();
            Set<org.ameyTech.Main.Node> closedList = new HashSet<>();

            ArrayList<org.ameyTech.Main.Node> path = new ArrayList<>();
            start.setGandH(0, heuristic(start, target));
            openList.offer(start);

            while (!openList.isEmpty()){
                org.ameyTech.Main.Node curr = openList.poll();
                closedList.add(curr);

                if(curr.equals(target)){
                    printPath(curr);
                    return;
                }

                // exploring neighbours
                for(int i=0;i<4;i++){
                    int nx = curr.x + dx[i];
                    int ny = curr.y + dy[i];

                    if(isValid(nx,ny)){
                        org.ameyTech.Main.Node neighbour = new org.ameyTech.Main.Node(nx, ny);
                        neighbour.setGandH(curr.g + 1, heuristic(neighbour, target));
                        neighbour.parent = curr;
                        openList.offer(neighbour);
                    }
                }

            }
        }

        private void printPath(org.ameyTech.Main.Node target){
            System.out.println("Path");
            org.ameyTech.Main.Node node = target;
            List<org.ameyTech.Main.Node> path = new ArrayList<>();

            while (node !=null){
                path.add(node);
                node = node.parent;
            }

            Collections.reverse(path);

            System.out.println(path);

        }

        private int heuristic(org.ameyTech.Main.Node a, org.ameyTech.Main.Node b){
            return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
        }

        private boolean isValid(int x, int y){
            return  (x>=0 && y>=0 && x<ROWS_MAX && y<ROWS_MAX && maze[x][y] == 0);
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

        org.ameyTech.Main.Node start = new org.ameyTech.Main.Node(0, 0);
        org.ameyTech.Main.Node end = new org.ameyTech.Main.Node(4, 4);

        org.ameyTech.Main.AstarAlgorithm astar = new org.ameyTech.Main.AstarAlgorithm(maze);
        astar.findPath(start, end);
    }
}