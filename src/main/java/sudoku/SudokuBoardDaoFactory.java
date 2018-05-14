package sudoku;

public class SudokuBoardDaoFactory {
    public Dao getFileDao(String fileName) {
        FileSudokuBoardDao dao = new FileSudokuBoardDao(fileName);
        return dao;
    }
}
