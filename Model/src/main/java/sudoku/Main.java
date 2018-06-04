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
        db.insertBoard(sb);

//        db.read("Board0");
    }
}
