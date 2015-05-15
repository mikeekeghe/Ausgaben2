package beans;

import java.io.Serializable;

/**
 * Created by Bernhard1 on 14.05.15.
 */
public class IncomePK implements Serializable{
    private int month;
    private int year;

    public IncomePK() {
    }

    public IncomePK(int month, int year) {
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncomePK incomePK = (IncomePK) o;

        if (month != incomePK.month) return false;
        return year == incomePK.year;

    }

    @Override
    public int hashCode() {
        int result = month;
        result = 31 * result + year;
        return result;
    }


}
