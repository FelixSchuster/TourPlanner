package at.fhtw.view;

import at.fhtw.exceptions.BadRequestException;
import at.fhtw.exceptions.FailedToParseImageFileException;
import at.fhtw.exceptions.FailedToSendRequestException;
import at.fhtw.exceptions.InternalServerErrorException;
import at.fhtw.models.Tour;
import at.fhtw.view.popUps.DialogView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTourView implements Initializable {
    private static final Logger logger = LogManager.getLogger(AddTourView.class);
    @FXML
    private Text feedbackText;
    @FXML
    public TextField tourNameTextField;
    @FXML
    public TextField descriptionTextField;
    @FXML
    private TextField startTextField;
    @FXML
    private TextField destinationTextField;
    @FXML
    private ChoiceBox<String> transportTypeChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle rb) {

        transportTypeChoiceBox.setValue("car");
        updateChoiceBoxWidth();
        transportTypeChoiceBox.setOnAction(event -> updateChoiceBoxWidth());
    }

    @FXML
    private void updateChoiceBoxWidth() {
        String selectedValue = transportTypeChoiceBox.getSelectionModel().getSelectedItem();
        double newWidth = selectedValue.length() * 10;
        System.out.println("length: " + selectedValue.length());
        transportTypeChoiceBox.setPrefWidth(newWidth);
    }

    public void addTourAction(ActionEvent event) {
        try {

            if (tourNameTextField.getText() == null ||
                    tourNameTextField.getText().isBlank() ||
                    tourNameTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a tourname!");
                return;
            } else if (descriptionTextField.getText() == null ||
                    descriptionTextField.getText().isBlank() ||
                    descriptionTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a description!");
                return;
            } else if (startTextField.getText() == null ||
                    startTextField.getText().isBlank() ||
                    startTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a start!");
                return;
            } else if (destinationTextField.getText() == null ||
                    destinationTextField.getText().isBlank() ||
                    destinationTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a destination!");
                return;
            }

            Tour tour = new Tour(tourNameTextField.getText(), descriptionTextField.getText(), startTextField.getText(), destinationTextField.getText(), transportTypeChoiceBox.getSelectionModel().getSelectedItem());
            ListToursView.getInstance().addTour(tour);
            new DialogView("Tour successfully created!", "Create Tour");

            ListToursView.getInstance().clearItems();
            ListToursView.getInstance().initList();

            resetTextfields();
        }
        catch (BadRequestException e) {
            logger.warn("AddTourViewModel.createTour() - " + e.getMessage());
            new DialogView("Bad Request\nTour could not be created!", "Create Tour");
            resetTextfields();
        } catch (InternalServerErrorException e) {
            logger.error("AddTourViewModel.createTour() - " + e.getMessage());
            new DialogView("Internal Server Issues\nTour could not be created!", "Create Tour");
            resetTextfields();
        } catch (FailedToSendRequestException e) {
            logger.error("AddTourViewModel.createTour() - " + e.getMessage());
            new DialogView("Failed to send Request\nTour could not be created!", "Create Tour");
            resetTextfields();
        } catch (FailedToParseImageFileException e) {
            logger.error("AddTourViewModel.createTour() - " + e.getMessage());
            new DialogView("Failed to parse image\nTour could not be created!", "Create Tour");
            resetTextfields();
        }
    }

    private void resetTextfields()
    {
        tourNameTextField.setText("");
        descriptionTextField.setText("");
        startTextField.setText("");
        destinationTextField.setText("");
        transportTypeChoiceBox.setValue("car");
        feedbackText.setText("");
    }
}
