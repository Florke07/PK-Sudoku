package sudoku;

import exceptions.EmptyFileNameException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaoTest {
    @Test
    void FileSudokuBoardTest_Write() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        Dao d=null;
        try {
            d = factory.getFileDao("test.txt");
        } catch (EmptyFileNameException ex) {
            ex.printStackTrace();
        }

        d.write(sb);

        File f = new File("test.txt");

        assertTrue(f.exists());
    }

    @Test
    void FileSudokuBoardTest_Read() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao d = null;
        try {
            d = factory.getFileDao("test.txt");
        } catch (EmptyFileNameException ex) {
            ex.printStackTrace();
        }
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        d.write(sb);

        SudokuBoard sb2 = (SudokuBoard)d.read();

        boolean a = false;
        for (int i=0;i<9;i++) {
            for (int j=0;j<9;j++) {
                if (sb.getValue(i, j) == sb2.getValue(i, j)) a = true;
                else a = false;
            }
        }

        assertTrue(a);
    }

}
