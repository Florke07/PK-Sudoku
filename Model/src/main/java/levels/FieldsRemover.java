package levels;

import sudoku.SudokuBoard;

import java.util.Random;

public class FieldsRemover {


    public static void removeField(final Difficulty dif, final SudokuBoard sb) {
        Random rng = new Random();
        int tmpX;
        int tmpY;
        switch (dif) {
            case EASY:
                for (int i = 0; i < 15; i++) {
                    tmpX = rng.nextInt(8);
                    tmpY = rng.nextInt(8);
                    sb.setValue(tmpX, tmpY, 0);
                    sb.getField(tmpX, tmpY).setModifiable(true);
                }
                break;
            case MEDIUM:
                for (int i = 0; i < 35; i++) {
                    tmpX = rng.nextInt(8);
                    tmpY = rng.nextInt(8);
                    sb.setValue(tmpX, tmpY, 0);
                    sb.getField(tmpX, tmpY).setModifiable(true);
                }
                break;
            case HARD:
                for (int i = 0; i < 50; i++) {
                    tmpX = rng.nextInt(8);
                    tmpY = rng.nextInt(8);
                    sb.setValue(tmpX, tmpY, 0);
                    sb.getField(tmpX, tmpY).setModifiable(true);
                }
                break;
        }
    }
}
