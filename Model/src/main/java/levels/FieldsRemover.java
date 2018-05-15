package levels;

import sudoku.SudokuBoard;

import java.util.Random;

public class FieldsRemover {
    public static void removeField(final Difficulty dif, final SudokuBoard sb) {
        Random rng = new Random();

        switch (dif) {
            case EASY:
                for (int i = 0; i < 15; i++) {
                    sb.setValue(rng.nextInt(8), rng.nextInt(8), 0);
                }
                break;
            case MEDIUM:
                for (int i = 0; i < 35; i++) {
                    sb.setValue(rng.nextInt(8), rng.nextInt(8), 0);
                }
                break;
            case HARD:
                for (int i = 0; i < 50; i++) {
                    sb.setValue(rng.nextInt(8), rng.nextInt(8), 0);
                }
                break;
        }
    }
}
