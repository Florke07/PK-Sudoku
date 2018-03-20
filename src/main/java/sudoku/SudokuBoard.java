package sudoku;

import java.util.ArrayList;
import java.util.Collections;

public class SudokuBoard {

    public int[][] board = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 5, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 9},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public ArrayList<Integer> valuesToInsert;
    private BacktrackingSudokuSolver BSS = new BacktrackingSudokuSolver();


    private void initValuesToInsert() {
        valuesToInsert = new ArrayList<Integer>();
        valuesToInsert.clear();
        for (int i = 1; i <= 9; i++) {
            valuesToInsert.add(i);
        }
    }

    public boolean fillBoard() {
        initValuesToInsert();
        if (BSS.solve(this, 0, 0)) {
            show();
            return true; } else {
            System.out.println("Brak rozwiazan");
            return false;
        }
    }

    private void show() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isOk(int currentRowPosition, int currentColumnPosition, int valueToCheck) {
        for (int i=0; i < 9; i++) {
            if (board[currentRowPosition][i] == valueToCheck) {
                return false;
            }
        }

        for (int i=0; i < 9; i++) {
            if (board[i][currentColumnPosition] == valueToCheck) {
                return false;
            }
        }

        int squareFirstRowNumber = 3 * (currentRowPosition / 3);
        int squareFirstColumnNumber = 3 * (currentColumnPosition / 3);

        int squareEndRowNumber = squareFirstRowNumber + 2;
        int squareEndColumnNumber = squareFirstColumnNumber + 2;

        for (int x = squareFirstRowNumber; x <= squareEndRowNumber; x++) {
            for (int y = squareFirstColumnNumber; y <= squareEndColumnNumber; y++) {
                if (board[x][y] == valueToCheck) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getValue(int x, int y) {
        return board[x][y];
    }

    public boolean checkBoard() {
        for (int i=0; i < 9; i++) {
            for (int j=0; j < 9; j++) {
                if (!isOk(i, j, getValue(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setValue(int x, int y, int value) {
        board[x][y]=value;
    }
}

