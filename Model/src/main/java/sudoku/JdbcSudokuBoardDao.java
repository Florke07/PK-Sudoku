package sudoku;

import database.Database;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    Database db = new Database();

    @Override
    public void close() throws Exception {

    }

    @Override
    public SudokuBoard read() {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {
        db.connect();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
