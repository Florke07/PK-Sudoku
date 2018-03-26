package SudokuParts;

import sudoku.SudokuField;

import java.util.ArrayList;

public abstract class SudokuElement {
    protected ArrayList<SudokuField> elements;

    public abstract boolean verify();

    public SudokuElement() {
        elements = new ArrayList<>();
    }
}
