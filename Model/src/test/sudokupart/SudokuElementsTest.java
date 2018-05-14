package sudokupart;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sudoku.SudokuBoard;

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

    @Test
    void sudokuBoxVerify() {
        SudokuBox bx = new SudokuBox();
        ArrayList<SudokuField> sf = new ArrayList<>();
        for (int i=1;i<=9;i++) {
            sf.add(new SudokuField(i));
        }
        bx.add(sf);
        assertTrue(bx.verify());
    }

    @Test
    void sudokuBoxVerifyBad() {
        SudokuBox sb = new SudokuBox();
        ArrayList<SudokuField> sf = new ArrayList<>();
        for (int i=1;i<=9;i++) {
            sf.add(new SudokuField(i));
        }
        sf.get(3).setFieldValue(3);
        sb.add(sf);
        assertFalse(sb.verify());
    }

    @Test
    void sudokuElementsEquals_NotEqual() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();

        SudokuElement se1 = sb.getColumn(0);
        SudokuElement se2 = sb.getColumn(5);

        assertFalse(se1.equals(se2));

    }

    @Test
    void sudokuElementsEquals_Null() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();

        SudokuElement se1 = sb.getColumn(0);
        SudokuElement se2 = null;

        assertFalse(se1.equals(se2));


    }

    @Test
    void sudokuElementsEquals_NotClass() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();

        SudokuElement se1 = sb.getColumn(0);

        assertFalse(se1.equals(sb));

    }
    @Test
    void sudokuElementsEquals_Equal() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        SudokuElement se = sb.getColumn(0);

        assertTrue(se.equals(se));
    }

    @Test
    void sudokuElementsHashCode_Equals() {
        int hs1, hs2;
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();

        SudokuElement se1 = sb.getColumn(0);
        SudokuElement se2 = sb.getColumn(0);
        hs1=se1.hashCode();
        hs2=se2.hashCode();

        assertEquals(hs1,hs2);
    }

    @Test
    void sudokuElementsHashCode_NotEquals() {
        int hs1, hs2;
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();

        SudokuElement se1 = sb.getColumn(0);
        SudokuElement se2 = sb.getColumn(5);

        hs1=se1.hashCode();
        hs2=se2.hashCode();

        assertNotEquals(hs1,hs2);
    }
    @Test
    void sudokuElementToString() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        for(int i = 0; i < 9;i++){
            sb.setValue(i,0,i+1);
        }
        SudokuElement se1 = sb.getColumn(0);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]",se1.toString());

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
    void sudokuFieldEquals_NotClass() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();

        SudokuField sf = sb.getField(0,0);
        SudokuColumn sc = sb.getColumn(5);

        assertFalse(sf.equals(sc));
    }
}