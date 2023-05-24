package at.fhtw.client.view;

import at.fhtw.client.viewmodel.AddTourViewModel;
import at.fhtw.client.viewmodel.MainViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AddTourView {

    @Autowired
    private AddTourViewModel addTourViewModel;
    @FXML
    private Button addButton;
    @FXML
    private Button reloadButton;
    @FXML
    private void initialize() {

        //searchField.textProperty().bindBidirectional(searchViewModel.searchStringProperty());

        // search panel
        addButton.setText("Add");
        reloadButton.setText("Reload");

        /*searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchLabel.setText(newValue);
        });*/
    }
}
