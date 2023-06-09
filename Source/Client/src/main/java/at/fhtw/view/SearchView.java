package at.fhtw.view;

import at.fhtw.exceptions.FailedToSendRequestException;
import at.fhtw.exceptions.InternalServerErrorException;
import at.fhtw.exceptions.NoContentException;
import at.fhtw.exceptions.NotFoundException;
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
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchLabel.setText(newValue);
            });
        }

    private void loadData() {
        try
        {
            ListToursViewModel toursViewModel = ListToursView.getInstance();

            if (searchField.getText() == null || searchField.getText().isBlank() || searchField.getText().isEmpty()) {
                toursViewModel.clearItems();
                toursViewModel.initList();
                return;
            }

            toursViewModel.clearItems();
            toursViewModel.filterList(searchField.getText());

        } catch (NoContentException e) {
            logger.info("SearchView.searchTour() - " + e.getMessage());
            new DialogView("No Content Found!", "Search Tour");
        } catch (NotFoundException e) {
            logger.info("SearchView.searchTour() - " + e.getMessage());
            new DialogView("No Tour Found!", "Search Tour");
        } catch (InternalServerErrorException e) {
            logger.error("SearchView.searchTour() - " + e.getMessage());
            new DialogView("Internal Server Issues\nTour could not be searched!", "Search Tour");
        } catch (FailedToSendRequestException e) {
            logger.error("SearchView.searchTour() - " + e.getMessage());
            new DialogView("Failed to send Request\nTour could not be searched!", "Search Tour");
        }
    }
}
