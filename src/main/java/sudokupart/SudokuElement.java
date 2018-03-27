package sudokupart;

import sudoku.SudokuField;

import java.util.ArrayList;

public class SudokuElement {
    protected ArrayList<SudokuField> elements;

    public boolean verify() {
        int counter = 0;
        for (int i = 1; i <= 9; i++) {
            for (SudokuField col : elements) {
                if (col.getFieldValue() == i) {
                    counter++;
                }
            }
            if (counter > 1) {
                return false;
            }
            counter = 0;
        }
        return true;
    }

    public void add(final ArrayList<SudokuField> elemnt) {
        for (int i = 0; i < elemnt.size(); i++) {
            elements.add(elemnt.get(i));
        }
    }

    public int getIndeksValue(int indeks) {
        return elements.get(indeks).getFieldValue();
    }

    public SudokuElement() {
        elements = new ArrayList<>();
    }
}
