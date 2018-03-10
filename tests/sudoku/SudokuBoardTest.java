package sudoku;

import org.junit.jupiter.api.Test;

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
        int board2[][] = new int[9][9];
        SudokuBoard sud = new SudokuBoard();
        sud.fillBoard();

        for (int i=0; i < 9; i++) {
            for (int j=0; j < 9; j++) {
                board2[i][j] = sud.getValue(i, j);
            }
        }

        sud.fillBoard();

        for (int i=0; i < 9; i++) {
            for (int j=0; j < 9; j++) {
                assertEquals(board2[i][j], sud.getValue(i, j));
            }
        }
    }
}