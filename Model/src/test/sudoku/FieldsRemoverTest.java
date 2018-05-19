package sudoku;

import levels.Difficulty;
import levels.FieldsRemover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldsRemoverTest {
    @Test
    void removerTest() {
        FieldsRemover fr = new FieldsRemover();
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();

        FieldsRemover.removeField(Difficulty.EASY, sb);
        boolean getIt = false;
        for (int i = 0;i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sb.getValue(i, j) == 0) {
                    getIt = true;
                    break;
                }
            }
        }

        assertEquals(true, getIt);
    }

}
