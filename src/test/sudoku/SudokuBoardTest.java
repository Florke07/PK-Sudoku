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
        int i, j;

        SudokuBoard sud = new SudokuBoard();
        sud.fillBoard();

        SudokuBoard sud2 = new SudokuBoard();
        sud2.fillBoard();

        i = rng.nextInt(8)+1;
        j = rng2.nextInt(8)+1;

        if (sud2.getValue(i, j) == sud.getValue(i, j) && sud2.getValue(j, i) == sud.getValue(j, i)) {
            assertTrue(false);
        } else { assertTrue(true); }


    }
}