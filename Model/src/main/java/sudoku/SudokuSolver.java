package sudoku;

public interface SudokuSolver {
    public boolean solve(final SudokuBoard sb, int currentRowPosition, int currentColumnPosition);
}
