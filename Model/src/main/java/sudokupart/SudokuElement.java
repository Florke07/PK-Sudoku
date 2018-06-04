package sudokupart;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;

import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;

public class SudokuElement implements Serializable, Cloneable {
    public ArrayList<SudokuField> elements;

    public boolean verify() {
        int counter = 0;
            for (SudokuField col : elements) {
                counter += col.getFieldValue();
            }
            if (counter == 45) {
                return true;
            } else {
                return false;
            }
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, SIMPLE_STYLE).
                append("elements", elements).
                toString();
    }
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuElement tmp = (SudokuElement) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(elements, tmp.elements)
                .isEquals();
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder(21, 37).
                append(elements).
                toHashCode();
    }

    @Override
    public SudokuElement clone() throws CloneNotSupportedException {
        return (SudokuElement) super.clone();
    }

}
