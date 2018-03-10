package sudoku;

public class SudokuBoard {

    private int[][] board = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

    public SudokuBoard() {
    }

    public void fillBoard(){
        //todo automatyczne rozwiazanie sudoku algorytmem backtrackingu
    }

    public void show(){
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                System.out.print(board[i][j]+" ");
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
}
