package sudokupart;

import exceptions.WrongValueException;
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
        try {
            sf.get(3).setFieldValue(3);
        } catch (WrongValueException ex) {
            ex.printStackTrace();
        }

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
        try {
            sf.get(3).setFieldValue(3);
        } catch (WrongValueException ex) {
            ex.printStackTrace();
        }

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
        try {
            sf.get(3).setFieldValue(3);
        } catch (WrongValueException ex) {
            ex.printStackTrace();
        }

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
            try {
                sb.setValue(i,0,i+1);
            } catch (WrongValueException ex) {
                ex.printStackTrace();
            }

        }
        SudokuElement se1 = sb.getColumn(0);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]",se1.toString());

    }

    @Test
    void sudokuElementClone() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        SudokuField sf = sb.getField(0,0);
        SudokuField sf2 = null;
        try {
            sf2 = sf.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }

        SudokuBoard sb2= null;
        try {
            sb2 = sb.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        try {
            sb.setValue(0,0,0);
        } catch (WrongValueException ex) {
            ex.printStackTrace();
        }


        assertNotEquals(sf.getFieldValue(),sf2.getFieldValue());
        assertNotEquals(sb.getValue(0,0),sb2.getValue(0,0));
    }

    @Test
    void elementClone() {
        SudokuElement sf = new SudokuElement();
        SudokuElement sf2 = new SudokuElement();
        try {
            sf2 = sf.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        assertNotEquals(sf,sf2);
    }
}