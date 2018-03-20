package sudoku;

import java.util.Collections;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public boolean solve(final SudokuBoard sb, int currentRowPosition, int currentColumnPosition) {
        boolean solved;

        Collections.shuffle(sb.valuesToInsert);

        if (currentColumnPosition == 9) {
            return true;
        }

        if (sb.board[currentRowPosition][currentColumnPosition] != 0) {
            if (!(currentRowPosition + 1 == 9)) {
                return solve(sb, currentRowPosition + 1, currentColumnPosition);
            }
        }
        for (int i = 1; i <= 9; i++) {
            boolean valid = sb.isOk(currentRowPosition, currentColumnPosition, sb.valuesToInsert.get(i - 1));

            if (!valid) {
                continue;
            }
            sb.board[currentRowPosition][currentColumnPosition] = sb.valuesToInsert.get(i - 1);
            if (currentRowPosition + 1 == 9) {
                solved = solve(sb, 0, currentColumnPosition + 1);
            } else {
                solved = solve(sb, currentRowPosition + 1, currentColumnPosition);
            }
            if (solved) {
                return true;
            } else {
                sb.board[currentRowPosition][currentColumnPosition] = 0;
            }
        }
        return false;
    }
}
