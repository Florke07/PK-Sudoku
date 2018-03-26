package sudoku;

public class SudokuField {
    private int value;

    public SudokuField() {
        value=0;
    }

    public  SudokuField(int value) {
        this.value=value;
    }

    public  int getFieldValue() {
        return value;
    }

    public void setFieldValue(int valueToSet) {
        value=valueToSet;
    }
}
