package gui.budget.createBudget;

import beans.Budget;
import beans.Category;
import enums.CategoryType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Bernhard1 on 15.05.15.
 */
public class CreateBudgetDlg {

    private ObservableList<TestProperty> budgetData = FXCollections.observableArrayList();

    public CreateBudgetDlg(){
        Category cat1 = new Category("Kategorie 1", null, 0, CategoryType.BILL);
        Category cat2 = new Category("Kategorie 2", null, 0, CategoryType.BILL);
        budgetData.add( new TestProperty("Kategorie 1", 1000, 02, 2015));
        budgetData.add( new TestProperty("Kategorie 2", 300, 02, 2015));



    }

    public void showCreateBudgetDlg(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CreateBudgetDlg.class.getResource("CreateBudget.fxml"));

            GridPane mainPane = (GridPane) loader.load();



        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ObservableList<TestProperty> getBudgetData() {
        return budgetData;
    }
}
