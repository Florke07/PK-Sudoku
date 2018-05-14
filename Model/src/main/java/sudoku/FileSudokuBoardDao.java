package sudoku;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    String filePath;
    @Override
    public SudokuBoard read() {
        String tmp;
        int index;
        SudokuBoard obj = new SudokuBoard();
        obj.makeBoard();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            for(int i = 0;i < 9;i++) {
                tmp = fileReader.readLine();
                System.out.println(tmp);
                for(int j = 1;j <= 9;j++){
                    index = tmp.indexOf(Integer.toString(j));
                    //System.out.println(index);
                    obj.setValue(i,((index-1)/3),j);
                }
            }
            System.out.println();
             return obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            for(int i = 0;i < 9;i++) {
                fileWriter.write(obj.getRow(i).toString());
                fileWriter.write("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    FileSudokuBoardDao(final String filePath) {
        this.filePath = filePath;
    }

    public void finalize() {

    }
}
