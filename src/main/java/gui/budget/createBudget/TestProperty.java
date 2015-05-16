package gui.budget.createBudget;

import javafx.beans.property.*;

/**
 * Created by Bernhard1 on 15.05.15.
 */
public class TestProperty {

    private StringProperty category;
    private FloatProperty value;
    private IntegerProperty month;
    private IntegerProperty year;

    public TestProperty(String category, float value, int month, int year){
        this.category = new SimpleStringProperty(category);
        this.value = new SimpleFloatProperty(value);
        this.month = new SimpleIntegerProperty(month);
        this.year= new SimpleIntegerProperty(year);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public float getValue() {
        return value.get();
    }

    public FloatProperty valueProperty() {
        return value;
    }

    public void setValue(float value) {
        this.value.set(value);
    }

    public int getMonth() {
        return month.get();
    }

    public IntegerProperty monthProperty() {
        return month;
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }
}
