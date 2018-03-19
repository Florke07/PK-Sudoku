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
        int i, j, flaga;

        SudokuBoard sud = new SudokuBoard();
        sud.fillBoard();

        SudokuBoard sud2 = new SudokuBoard();
        sud2.fillBoard();

        for (int k = 0;k < 9;k++ ){
            for(int l = 0;k < 9;l++ ){
                if(sud.get(k,l) == sud2.get(k,l)) {
                    flaga++;
                }
            }
        }
        assertNotEquals(0,flaga);


    }
}