package sudoku;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {


    @Test
    void fillBoard() {
        SudokuBoard sud = new SudokuBoard();
        sud.fillBoard();

        for (int i=0; i < 9; i++) {
            for (int j=0; j < 9; j++) {
                assertEquals(false, sud.isOk(i, j, sud.getValue(i, j)));
            }
        }
    }

    @Test
    void secondFillBoard() {

        Random rng = new Random();
        Random rng2 = new Random();
        int i, j, flaga=0;

        SudokuBoard sud = new SudokuBoard();
        sud.fillBoard();

        SudokuBoard sud2 = new SudokuBoard();
        sud2.fillBoard();

        for (int k = 0;k < 9; k++ ) {
            for(int l = 0;l < 9; l++ ) {
                if(sud.getValue(k,l) == sud2.getValue(k,l)) {
                    flaga++;
                }
            }
        }
        assertNotEquals(0,flaga);
    }
    @Test
    void checkBoardTest() {
        SudokuBoard sud = new SudokuBoard();
        assertEquals(true, sud.fillBoard());
        sud.setValue(0,0,9);
        sud.setValue(1,0,9);
        assertEquals(false, sud.checkBoard());

    }

    @Test
    void checkBroad() {
        int flaga=0;
        boolean isFlagaTrue, isChechOK;
        SudokuBoard sud = new SudokuBoard();
        sud.fillBoard();

        SudokuBoard sud2 = new SudokuBoard();
        sud2.fillBoard();

        for (int k = 0;k < 9; k++ ) {
            for(int l = 0;l < 9; l++ ) {
                if(sud.getValue(k,l) == sud2.getValue(k,l)) {
                    flaga++;
                }
            }
        }
        if (flaga == 0) {
            isFlagaTrue=true;
        } else {
            isFlagaTrue=false;
        }
        isChechOK=sud.checkBoard();
        assertNotEquals(0,flaga);
        assertEquals(isFlagaTrue,isChechOK);
    }

    @Test
    void sudRowTest() {
        SudokuBoard sud  = new SudokuBoard();
        sud.fillBoard();
        int licznik=0;
        for (int i=0;i<9;i++) {
            if (sud.getRow(2).getIndeksValue(i) == sud.board2.get(2).get(i).getFieldValue()) licznik++;
        }
        assertEquals(9,licznik);
    }

    @Test
    void sudColTest() {
        SudokuBoard sud = new SudokuBoard();
        sud.fillBoard();
        int licznik=0;
        for (int i=0;i<9;i++){
            if (sud.getColumn(2).getIndeksValue(i) == sud.board2.get(i).get(2).getFieldValue()) licznik++;
        }
        assertEquals(9,licznik);
    }

    @Test
    void sudBoxTest() {
        SudokuBoard sud = new SudokuBoard();
        sud.fillBoard();
        sud.setValue(0,0,1);
        sud.setValue(0,1,1);
        assertFalse(sud.getBox(0,0).verify());
    }



}