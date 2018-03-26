package SudokuParts;

import org.junit.jupiter.api.Test;
import sudoku.SudokuBoard;
import sudoku.SudokuField;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SudokuElementsTest {

    @Test
    void sudokuRowVerifyOK() {
        SudokuRow rw = new SudokuRow();
        ArrayList<SudokuField> sf = new ArrayList<>();
        for (int i=1;i<=9;i++) {
            sf.add(new SudokuField(i));
        }
        rw.add(sf);
        assertTrue(rw.verify());
    }

    @Test
    void sudokuRowVerifyBad() {
        SudokuRow rw = new SudokuRow();
        ArrayList<SudokuField> sf = new ArrayList<>();
        for (int i=1;i<=9;i++) {
            sf.add(new SudokuField(i));
        }
        sf.get(3).setFieldValue(3);
        rw.add(sf);
        assertFalse(rw.verify());
    }

    @Test
    void sudokuColumnVerifyOK() {
        SudokuColumn cl = new SudokuColumn();
        ArrayList<SudokuField> sf = new ArrayList<>();
        for (int i=1;i<=9;i++) {
            sf.add(new SudokuField(i));
        }
        cl.add(sf);
        assertTrue(cl.verify());
    }

    @Test
    void sudokuColumnVerifyBad() {
        SudokuColumn cl = new SudokuColumn();
        ArrayList<SudokuField> sf = new ArrayList<>();
        for (int i=1;i<=9;i++) {
            sf.add(new SudokuField(i));
        }
        sf.get(3).setFieldValue(3);
        cl.add(sf);
        assertFalse(cl.verify());
    }
}