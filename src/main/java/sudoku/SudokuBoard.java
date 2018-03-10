package sudoku;

public class SudokuBoard {

    private int[][] board = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public void fillBoard() {
        //todo automatyczne rozwiazanie sudoku algorytmem backtrackingu
        if (solve(0, 0)) {
            show();
        } else {
            System.out.println("Brak rozwiazan");
        }
    }

    private void show() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isOk(int currentRowPosition, int currentColumnPosition, int valueToCheck) {
        for (int i=0; i < 9; i++) {
            if (board[currentRowPosition][i] == valueToCheck) {
                return false;
            }
        }

        for (int i=0; i < 9; i++) {
            if (board[i][currentColumnPosition] == valueToCheck) {
                return false;
            }
        }


        int squareFirstRowNumber = 3 * (currentRowPosition / 3);
        int squareFirstColumnNumber = 3 * (currentColumnPosition / 3);

        int squareEndRowNumber = squareFirstRowNumber + 2;
        int squareEndColumnNumber = squareFirstColumnNumber + 2;

        for (int x = squareFirstRowNumber; x <= squareEndRowNumber; x++) {
            for (int y = squareFirstColumnNumber; y <= squareEndColumnNumber; y++) {
                if (board[x][y] == valueToCheck) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getValue(int i, int j) {
        return board[i][j];
    }

    private boolean solve(int currentRowPosition, int currentColumnPosition) {
        boolean solved;
        if (currentColumnPosition == 9) {
            return true;
        }

        if (board[currentRowPosition][currentColumnPosition] != 0) {
            if (currentRowPosition + 1 == 9) {
                return solve(0, currentColumnPosition + 1);
            } else {
                return solve(currentRowPosition + 1, currentColumnPosition);
            }
        }
        for (int i = 1; i <= 9; i++) {
            boolean valid = isOk(currentRowPosition, currentColumnPosition, i);

            if (!valid) {
                continue;
            }
            board[currentRowPosition][currentColumnPosition] = i;
            if (currentRowPosition + 1 == 9) {
                solved = solve(0, currentColumnPosition + 1);
            } else {
            solved = solve(currentRowPosition + 1, currentColumnPosition);
            }
            if (solved) {
                return true;
            } else {
                board[currentRowPosition][currentColumnPosition] = 0;
            }
        }
        return false;
    }
}

