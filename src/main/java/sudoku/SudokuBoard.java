package sudoku;

public class SudokuBoard {

    private int[][] board = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public SudokuBoard() {
    }

    public void fillBoard() {
        //todo automatyczne rozwiazanie sudoku algorytmem backtrackingu
        if(solve(1,0,0))
            show();
        else
            System.out.println("Brak rozwiazan");

    }

    public void show() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isOK(int currentRowPosition, int currentColumnPosition, int valueToCheck){

        for(int i=0;i<9;i++)
            if (board[currentRowPosition][i] == valueToCheck) return false;

        for (int i=0;i<9;i++)
            if (board[i][currentColumnPosition] == valueToCheck) return false;


        int squareFirstRowNumber = 3 * (currentRowPosition/3);
        int squareFirstColumnNumber = 3 * (currentColumnPosition/3);

        int squareEndRowNumber = squareFirstRowNumber + 2;
        int squareEndColumnNumber = squareFirstColumnNumber + 2;

        for (int x = squareFirstRowNumber; x <= squareEndRowNumber; x++)
            for (int y = squareFirstColumnNumber; y <= squareEndColumnNumber; y++)
                if (board[x][y] == valueToCheck) return false;


        return true;
    }
    public boolean solve(int guess,int x,int y){
        if(guess == 10){
            return false;
        } else {
            if (board[x][y] != 0) {
                if (isOK(x,y,guess)) {
                    if (x + 1 == 9) {
                        x = 1;
                        if (y + 1 == 9) {
                            return true;
                        }
                        solve(1, x, y + 1);
                    } else {
                        solve(1, x + 1, y);
                    }
                } else {
                    solve(guess + 1, x, y);
                }
            }
        }
        return false;
    }
}
