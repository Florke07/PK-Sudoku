package sudoku;

import database.Database;

public class Main {
    public static void main(final String[] args) {
        Database db = new Database();
        db.connect();
        //db.deleteDB();
        //db.createTableUsers();
        //db.selectUsers();
        //System.out.println("Dodajmy");
        //db.addToUsers();
        //db.selectUsers();
        //db.deleteDB();
        //db.drop();
        db.createTableFields();
        db.createTableColumns();
        db.createTableBoards();
    }
}
