package sudoku;

import exceptions.EmptyFileNameException;

public class SudokuBoardDaoFactory {
    public Dao getFileDao(final String fileName) throws EmptyFileNameException {
        if (fileName.isEmpty()) {
            throw new EmptyFileNameException("Path to file is empty!");
        }
        FileSudokuBoardDao dao = new FileSudokuBoardDao(fileName);
        return dao;
    }
}
