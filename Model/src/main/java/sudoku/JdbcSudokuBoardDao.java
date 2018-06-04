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
        db.connect();

        db.shutdownDB();
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {
        db.connect();
        db.insertBoard(obj);
        db.shutdownDB();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
