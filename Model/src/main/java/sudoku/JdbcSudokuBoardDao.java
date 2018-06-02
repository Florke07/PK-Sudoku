package sudoku;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    @Override
    public void close() throws Exception {

    }

    @Override
    public SudokuBoard read() {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
