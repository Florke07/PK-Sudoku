package sudoku;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String filePath;

    @Override
    public SudokuBoard read() {
        /*String tmp;
        int index;
        SudokuBoard obj = new SudokuBoard();
        obj.makeBoard();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < 9; i++) {
                tmp = fileReader.readLine();
                for (int j = 1; j <= 9; j++) {
                    index = tmp.indexOf(Integer.toString(j));
                    obj.setValue(i, ((index - 1) / 3), j);
                }
            }
            System.out.println();
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        SudokuBoard obj;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = (SudokuBoard) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Wczyt zakonczony");
            return obj;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return null;
        }
    }

    @Override
    public void write(final SudokuBoard obj) {
        /*
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < 9; i++) {
                fileWriter.write(obj.getRow(i).toString());
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + filePath);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    FileSudokuBoardDao(final String filePath) {
        this.filePath = filePath;
    }

    public void finalize() {

    }
}
