package sudokupart;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;

public class SudokuField implements Comparable, Serializable, Cloneable {
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
    @Override
    public String toString() {
        return new ToStringBuilder(this, SIMPLE_STYLE).
                append("value", value).
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
        SudokuField tmp = (SudokuField) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(value, tmp.value)
                .isEquals();
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder(21, 37).
                append(value).
                toHashCode();
    }

    @Override
    public int compareTo(final Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (value == ((SudokuField) o).value) {
            return 0;
        } else
        if (this.value > ((SudokuField) o).value) {
            return ((SudokuField) o).value - value;
        } else {
            return ((SudokuField) o).value - value;
        }

    }
}
