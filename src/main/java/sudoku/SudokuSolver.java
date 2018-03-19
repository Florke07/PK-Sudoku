package sudoku;

public interface SudokuSolver {
    public boolean solve(SudokuBoard sb, int currentRowPosition, int currentColumnPosition);
}
