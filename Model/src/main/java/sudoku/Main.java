package sudoku;

import database.Database;

public class Main {
    public static void main(final String[] args) {
        Database db = new Database();
        db.connect();
        db.dropTables();
        db.createTables();
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        for (int i=0;i<9;i++) {
            for (int j=0;j<9;j++) {
                System.out.println(sb.getValue(i, j));
            }
        }
        db.insertBoard(sb);
    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        sb = db.read("Board0");

        for (int i=0;i<9;i++) {
            for (int j=0;j<9;j++) {
                System.out.println(sb.getValue(i, j));
            }
        }

    }
}
