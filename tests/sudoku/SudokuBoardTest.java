package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {


    @Test
    void fillBoard() {
        SudokuBoard sud = new SudokuBoard();
        sud.fillBoard();

        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if(sud.isOk(i,j,sud.getValue(i,j))) {
                    System.out.println("Error, wrong number in board");
                    return;
                }
            }
        }
    }

    @Test
    void secondFillBoard() {
        int board2[][] = new int[9][9];
        SudokuBoard sud = new SudokuBoard();
        sud.fillBoard();

        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                board2[i][j]=sud.getValue(i,j);
            }
        }

        sud.fillBoard();

        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if (board2[i][j] != sud.getValue(i,j)) {
                    System.out.println("Error");
                    break;
                }
            }
        }
    }
}