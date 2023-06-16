package at.fhtw.view;

import at.fhtw.viewmodel.ListToursViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchView {
    private static final Logger logger = LogManager.getLogger(SearchView.class);

    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Label searchLabel;

    @FXML
    private void initialize() {
        searchButton.setText("Search");
        searchButton.setOnAction(event -> loadData());
        searchButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        searchField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                loadData();
            }
        });
    }

    private void loadData() {
        ListToursViewModel toursViewModel = ListToursView.getInstance();

        if (searchField.getText() == null || searchField.getText().isBlank() || searchField.getText().isEmpty()) {
            reload();
            return;
        }

        reload();
        toursViewModel.filterList(searchField.getText());
    }

    public void reload() {
        ListToursView.getInstance().clearItems();
        ListToursView.getInstance().initList();
        ShowTourInformationView.getInstance().hideInformation();
        ShowTourLogsView.getInstance().hideTourLogs();
    }
}
