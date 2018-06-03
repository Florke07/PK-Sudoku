package sudoku;

import database.Database;

public class Main {
    public static void main(final String[] args) {
        Database db = new Database();
        db.connect();
        db.dropTables();
        db.createTables();
    }
}
