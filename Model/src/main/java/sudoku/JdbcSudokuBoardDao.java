package sudoku;

import database.Database;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    Database db = new Database();

    @Override
    public void close() throws Exception {
        db.shutdownDB();
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard ns = new SudokuBoard();
        db.connect();
        ns = db.read("Board0");
        db.shutdownDB();
        return ns;
    }

    @Override
    public void write(SudokuBoard obj) {
        db.connect();
        db.dropTables();
        db.createTables();
        db.insertBoard(obj);
        db.shutdownDB();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
