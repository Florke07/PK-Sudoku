package sudoku;

import exceptions.BacktrackingException;

public interface SudokuSolver {
    public boolean solve(final SudokuBoard sb, int currentRowPosition, int currentColumnPosition) throws BacktrackingException;
}
