package sudoku;

import exceptions.BacktrackingException;
import exceptions.WrongValueException;

import java.util.Collections;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public boolean solve(final SudokuBoard sb, int currentRowPosition, int currentColumnPosition) throws BacktrackingException {
        boolean solved;

        Collections.shuffle(sb.valuesToInsert);

        if (currentColumnPosition == 9) {
            return true;
        }

        if (sb.board2.get(currentRowPosition).get(currentColumnPosition).getFieldValue() != 0) {
            if (!(currentRowPosition + 1 == 9)) {
                return solve(sb, currentRowPosition + 1, currentColumnPosition);
            }
        }
        for (int i = 1; i <= 9; i++) {
            boolean valid = sb.isOk(currentRowPosition, currentColumnPosition, sb.valuesToInsert.get(i - 1));

            if (!valid) {
                continue;
            }
            try {
                sb.board2.get(currentRowPosition).get(currentColumnPosition).setFieldValue(sb.valuesToInsert.get(i - 1));
            } catch (WrongValueException ex) {

            }

            if (currentRowPosition + 1 == 9) {
                solved = solve(sb, 0, currentColumnPosition + 1);
            } else {
                solved = solve(sb, currentRowPosition + 1, currentColumnPosition);
            }
            if (solved) {
                return true;
            } else {
                try {
                    sb.board2.get(currentRowPosition).get(currentColumnPosition).setFieldValue(0);
                } catch (WrongValueException ex) {
                    throw new BacktrackingException("Backtracking Exception!");
                }

            }
        }
        return false;
    }
}
