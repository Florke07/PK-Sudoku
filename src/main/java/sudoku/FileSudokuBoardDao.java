package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    String filePath;
    @Override
    public SudokuBoard read() {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {

    }
    public void close() {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            fileReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    FileSudokuBoardDao(String filePath){
        this.filePath = filePath;
    }

    public void finalize() {

    }
}
