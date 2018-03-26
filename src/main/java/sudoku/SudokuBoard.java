package sudoku;

import sudokupart.SudokuBox;
import sudokupart.SudokuColumn;
import sudokupart.SudokuRow;

import java.util.ArrayList;

public class SudokuBoard {

    /*
    public int[][] board = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 5, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 9},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};*/

    public SudokuField[][] board = new SudokuField[9][9];

    public ArrayList<Integer> valuesToInsert;
    private BacktrackingSudokuSolver BSS = new BacktrackingSudokuSolver();


    private void initValuesToInsert() {
        valuesToInsert = new ArrayList<Integer>();
        valuesToInsert.clear();
        for (int i = 1; i <= 9; i++) {
            valuesToInsert.add(i);
        }
    }

    private void makeBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j]= new SudokuField();
            }
        }
    }

    public boolean fillBoard() {
        initValuesToInsert();
        makeBoard();
        if (BSS.solve(this, 0, 0)) {
            show();
            return true;
        } else {
            System.out.println("Brak rozwiazan");
            return false;
        }
    }

    private void show() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getFieldValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isOk(int currentRowPosition, int currentColumnPosition, int valueToCheck) {
        for (int i=0; i < 9; i++) {
            if (board[currentRowPosition][i].getFieldValue() == valueToCheck) {
                return false;
            }
        }

        for (int i=0; i < 9; i++) {
            if (board[i][currentColumnPosition].getFieldValue() == valueToCheck) {
                return false;
            }
        }

        int squareFirstRowNumber = 3 * (currentRowPosition / 3);
        int squareFirstColumnNumber = 3 * (currentColumnPosition / 3);

        int squareEndRowNumber = squareFirstRowNumber + 2;
        int squareEndColumnNumber = squareFirstColumnNumber + 2;

        for (int x = squareFirstRowNumber; x <= squareEndRowNumber; x++) {
            for (int y = squareFirstColumnNumber; y <= squareEndColumnNumber; y++) {
                if (board[x][y].getFieldValue() == valueToCheck) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getValue(int x, int y) {
        return board[x][y].getFieldValue();
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
        board[x][y].setFieldValue(value);
    }

    public SudokuRow getRow(int y) {
        SudokuRow row = new SudokuRow();
        ArrayList<SudokuField> sf = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sf.add(board[i][j]);
            }
            row.add(sf);
        }
        return row;
    }

    public SudokuColumn getColumn(int x) {
        SudokuColumn col = new SudokuColumn();
        ArrayList<SudokuField> sf =new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = i; j < 9; j += 9) {
                sf.add(board[i][j]);
            }
        col.add(sf);
        }
        return col;
    }

    public SudokuBox getBox(int x, int y) {
        SudokuBox sb = new SudokuBox();
        ArrayList<SudokuField> sf = new ArrayList<>();
        int squareFirstRowNumber = 3 * (x / 3);
        int squareFirstColumnNumber = 3 * (y / 3);

        int squareEndRowNumber = squareFirstRowNumber + 2;
        int squareEndColumnNumber = squareFirstColumnNumber + 2;

        for (int i = squareFirstRowNumber; i <= squareEndRowNumber; i++) {
            for (int j = squareFirstColumnNumber; j <= squareEndColumnNumber; j++) {
                sf.add(board[i][j]);
            }
        }
        sb.add(sf);
        return sb;
    }
}

