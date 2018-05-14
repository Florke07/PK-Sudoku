package sudoku;

public class SudokuBoardDaoFactory {
    public Dao getFileDao(final String fileName) {
        FileSudokuBoardDao dao = new FileSudokuBoardDao(fileName);
        return dao;
    }
}
