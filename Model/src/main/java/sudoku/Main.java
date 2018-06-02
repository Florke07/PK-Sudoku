package sudoku;

import database.Create;

public class Main {
    public static void main(final String[] args) {
        Create db = new Create();
        db.connect();
        //db.deleteDB();
        db.createTableUsers();
        db.selectUsers();
        System.out.println("Dodajmy");
        db.addToUsers();
        db.selectUsers();
        db.deleteDB();
    }
}
