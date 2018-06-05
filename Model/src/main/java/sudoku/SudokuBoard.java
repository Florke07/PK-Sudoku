package sudoku;

import exceptions.BacktrackingException;
import exceptions.WrongValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudokupart.SudokuBox;
import sudokupart.SudokuColumn;
import sudokupart.SudokuField;
import sudokupart.SudokuRow;

import java.io.Serializable;
import java.util.ArrayList;

public class SudokuBoard implements Serializable, Cloneable {

    ArrayList<ArrayList<SudokuField>> board2;
    transient BacktrackingSudokuSolver BSS = new BacktrackingSudokuSolver();
    transient ArrayList<Integer> valuesToInsert;
    final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);

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
                try {
                    setValue(i, j, board.get(i).get(j).getFieldValue());
                } catch (WrongValueException ex) {
                    ex.printStackTrace();
                }

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
        boolean bss = false;
        try {
            bss = BSS.solve(this, 0, 0);
        } catch (BacktrackingException ex) {
            ex.printStackTrace();
        }
        if (bss) {
            return true;
        } else {
            logger.info("Brak rozwiazan");
            return false;
        }
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
        ArrayList<SudokuColumn> sc = new ArrayList<>();
        int licznik=0;
        for (int i = 0; i < 9; i++) {
            sc.add(getColumn(i));
        }
        for (SudokuColumn c: sc) {
            if (c.verify()) {
                licznik++;
            }

        }
        if (licznik == 9) {
            return true;
        } else {
            return false;
        }
    }

    public void setValue(int x, int y, int value) throws WrongValueException {
        if (value < 0 || value > 9) {
            throw new WrongValueException("Value must be between 0 nad 9");
        }
        board2.get(x).get(y).setFieldValue(value);
    }

    public void setValue(int y, int x, int value, boolean modify)  {
        try {
            board2.get(x).get(y).setFieldValue(value);
            board2.get(y).get(x).setModifiable(modify);
        } catch (WrongValueException wrr) {

        }
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

