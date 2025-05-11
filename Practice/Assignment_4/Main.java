import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static class NQueens{
        private int N;
        private char[][] board;
        private ArrayList<ArrayList<String>> solutions = new ArrayList<>();

        public NQueens(int N){
            this.N = N;
            board = new char[N][N];
            initializeBoard();
        }

        public void solve(){
            solveNQueens(0);
            printSolutions();
        }

        public void initializeBoard(){
            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
                    board[i][j] = '.';
                }
            }
        }

        private void printSolutions() {
            if (solutions.isEmpty()) {
                System.out.println("No solutions found.");
                return;
            }

            System.out.println(solutions.size() + " solution(s) found.\n");

            int count = 1;
            for (ArrayList<String> solution : solutions) {
                System.out.println("=== Solution " + count++ + " ===");
                printBoard(solution);
                System.out.println();
            }
        }

        private void printBoard(ArrayList<String> board){
            int width = board.get(0).length() * 3 + 4;
            String border = "-".repeat(width);

            System.out.println(border);
            for(String row: board){
                System.out.print("|  ");
                for(int i=0; i<row.length(); i++){
                    System.out.print(row.charAt(i)+"  ");
                }
                System.out.println(" |");
            }
            System.out.println(border);

        }

        public void solveNQueens(int row){
            if(row == N){
                ArrayList<String> soln = new ArrayList<>();
                for (int i=0; i<N; i++){
                    soln.add(new String(board[i]));
                }
                solutions.add(soln);
                return;
            }

            for (int col=0; col<N; col++){

                if(isSafe(row, col)){
                    board[row][col] = 'Q';
                    solveNQueens(row+1);
                    board[row][col] = '.';
                }
            }
        }

        public boolean isSafe(int x, int y){

            for(int i=0; i<N;i++){
                if(board[i][y] == 'Q') return false;
            }

            int i=x;
            int j=y;

            while (i>=0 && j>=0){
                if(board[i][j] == 'Q') return false;
                i--;
                j--;
            }

            i=x;
            j=y;

            while (i>=0 && j<N){
                if(board[i][j] == 'Q') return false;
                i--;
                j++;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        int N = 5;  // You can change this to any size
        NQueens solver = new NQueens(N);
        solver.solve();
    }

}