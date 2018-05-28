package sudoku;

import sudokupart.SudokuBox;
import sudokupart.SudokuColumn;
import sudokupart.SudokuField;
import sudokupart.SudokuRow;

import java.io.Serializable;
import java.util.ArrayList;

public class SudokuBoard implements Serializable, Cloneable {

    ArrayList<ArrayList<SudokuField>> board2;
    transient private BacktrackingSudokuSolver BSS = new BacktrackingSudokuSolver();
    transient public ArrayList<Integer> valuesToInsert;

    public SudokuBoard() {
        board2 = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            board2.add(new ArrayList<>(9));
        }
    }

    public SudokuBoard(final ArrayList<ArrayList<SudokuField>> board) {
        board2 = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            board2.add(new ArrayList<>(9));
        }
        makeBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                setValue(i, j, board.get(i).get(j).getFieldValue());

            }

        }

    }


    public SudokuField getField(int x, int y) {
        return board2.get(y).get(x);
    }

    private void initValuesToInsert() {
        valuesToInsert = new ArrayList<>();
        valuesToInsert.clear();
        for (int i = 1; i <= 9; i++) {
            valuesToInsert.add(i);
        }
    }

    void makeBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board2.get(i).add(new SudokuField());
            }
        }
    }

    public boolean fillBoard() {
        initValuesToInsert();
        makeBoard();
        if (BSS.solve(this, 0, 0)) {
            //show();
            return true;
        } else {
            System.out.println("Brak rozwiazan");
            return false;
        }
    }

    public void show() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board2.get(i).get(j).getFieldValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isOk(int currentRowPosition, int currentColumnPosition, int valueToCheck) {
        for (int i=0; i < 9; i++) {
            if (board2.get(currentRowPosition).get(i).getFieldValue() == valueToCheck) {
                return false;
            }
        }

        for (int i=0; i < 9; i++) {
            if (board2.get(i).get(currentColumnPosition).getFieldValue() == valueToCheck) {
                return false;
            }
        }

        int squareFirstRowNumber = 3 * (currentRowPosition / 3);
        int squareFirstColumnNumber = 3 * (currentColumnPosition / 3);

        int squareEndRowNumber = squareFirstRowNumber + 2;
        int squareEndColumnNumber = squareFirstColumnNumber + 2;

        for (int x = squareFirstRowNumber; x <= squareEndRowNumber; x++) {
            for (int y = squareFirstColumnNumber; y <= squareEndColumnNumber; y++) {
                if (board2.get(x).get(y).getFieldValue() == valueToCheck) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getValue(int x, int y) {
        return board2.get(x).get(y).getFieldValue();
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
        board2.get(x).get(y).setFieldValue(value);
    }

    public SudokuRow getRow(int y) {
        int var = y % 9;
        SudokuRow row = new SudokuRow();
        ArrayList<SudokuField> sf = new ArrayList<>();
        int i = var;
            for (int j = 0; j < 9; j++) {
                sf.add(board2.get(i).get(j));
            }
            row.add(sf);
        return row;
    }

    public SudokuColumn getColumn(int x) {
        int var = x % 9;
        SudokuColumn col = new SudokuColumn();
        ArrayList<SudokuField> sf =new ArrayList<>();
            for (int j = 0; j < 9; j = j + 1) {
                sf.add(board2.get(j).get(var));
            }
        col.add(sf);
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
                sf.add(board2.get(i).get(j));
            }
        }
        sb.add(sf);
        return sb;
    }

    @Override
    public SudokuBoard clone() throws CloneNotSupportedException {
        return new SudokuBoard(board2);
    }

}

