package sudokupart;

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
        SudokuBoard sb1 = new SudokuBoard();
        sb1.fillBoard();
        SudokuBoard sb2 = new SudokuBoard();
        sb2.fillBoard();

        SudokuElement sc1 = sb1.getColumn(0);
        SudokuElement sc2 = sb2.getColumn(0);

        assertEquals(false, sc1.equals(sc2));

    }
    @Test
    void sudokuElementsEquals_Equal() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        SudokuElement sc = sb.getColumn(0);

        System.out.println(sc.toString());

        assertEquals(true, sc.equals(sc));
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
}