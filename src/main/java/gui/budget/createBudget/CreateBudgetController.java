package gui.budget.createBudget;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Bernhard1 on 15.05.15.
 */
public class CreateBudgetController {
    @FXML
    public TableView tbBudget;
    @FXML
    public TableColumn tcCategory;
    @FXML
    public TableColumn tcValue;

    private CreateBudgetDlg mainApp;

    @FXML
    private void initialize(){
        tcCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        tcValue.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
    }

    public void onAddCategory(ActionEvent actionEvent) {

    }

    public void setMainApp(CreateBudgetDlg mainApp){
        this.mainApp = mainApp;

        tbBudget.setItems(mainApp.getBudgetData());
    }
}
