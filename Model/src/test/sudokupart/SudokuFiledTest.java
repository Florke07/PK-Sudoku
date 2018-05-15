package sudokupart;

import org.junit.jupiter.api.Test;
import sudoku.SudokuBoard;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFiledTest {

    @Test
    void sudokuFieldEquals_NotClass() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();

        SudokuField sf = sb.getField(0,0);
        SudokuColumn sc = sb.getColumn(5);

        assertFalse(sf.equals(sc));
    }

    @Test
    void sudokuFieldEquals_NotEquals() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();

        SudokuField sf = sb.getField(0,0);
        SudokuField sf2 = sb.getField(0,1);

        assertFalse(sf.equals(sf2));
    }

    @Test
    void sudokuFieldEquals_Equals() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();

        SudokuField sf = board.getField(0,0);
        SudokuField sf2 = board.getField(0,0);

        assertTrue(sf.equals(sf2));
    }

    @Test
    void sudokuFieldEquals_Null() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();

        SudokuField sf = sb.getField(0,0);
        SudokuField sf2 = null;

        assertFalse(sf.equals(sf2));
    }

    @Test
    void sudokuFieldCompareTo_NullPointerException() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        SudokuField sf = sb.getField(0, 0);
        assertThrows(NullPointerException.class, () -> sf.compareTo(null));
    }

    @Test
    void sudokuFieldCompareTo_Equal() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        SudokuField sf = sb.getField(0, 0);
        SudokuField sf2 = sb.getField(0 ,0);

        assertEquals(0, sf.compareTo(sf2));
    }

    @Test
    void sudokuFieldCompareTo_Below() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        SudokuField sf = sb.getField(0, 0);
        SudokuField sf2 = new SudokuField();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sb.getValue(i ,j) < sf.getFieldValue()) {
                    sf2 = sb.getField(i ,j);

                } else {}
            }
        }
        assertEquals(sf2.getFieldValue()-sf.getFieldValue(), sf.compareTo(sf2));
    }

    @Test
    void sudokuFieldCompareTo_Higher() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        SudokuField sf = sb.getField(0, 0);
        SudokuField sf2 = new SudokuField();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sb.getValue(i ,j) > sf.getFieldValue()) {
                    sf2 = sb.getField(i ,j);
                }
            }
        }
        assertEquals(sf2.getFieldValue()-sf.getFieldValue(), sf.compareTo(sf2));
    }
}
