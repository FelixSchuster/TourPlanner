package at.fhtw.view;

import at.fhtw.exceptions.BadRequestException;
import at.fhtw.exceptions.FailedToParseImageFileException;
import at.fhtw.exceptions.FailedToSendRequestException;
import at.fhtw.exceptions.InternalServerErrorException;
import at.fhtw.models.TourListEntry;
import at.fhtw.viewmodel.AddTourViewModel;
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
    private static final Logger logger = LogManager.getLogger(AddTourViewModel.class);
    private AddTourViewModel addTourViewModel;
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
    @FXML
    private Text hiddenText;

    @Override
    public void initialize(URL location, ResourceBundle rb) {

        addTourViewModel = new AddTourViewModel();
        tourNameTextField.textProperty().bindBidirectional(addTourViewModel.tourNameProperty());
        descriptionTextField.textProperty().bindBidirectional(addTourViewModel.descriptionProperty());
        startTextField.textProperty().bindBidirectional(addTourViewModel.startProperty());
        destinationTextField.textProperty().bindBidirectional(addTourViewModel.destinationProperty());
        transportTypeChoiceBox.valueProperty().bindBidirectional(addTourViewModel.selectedTransportTypeOptionProperty());

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

            addTourViewModel.addTour();
            new AboutDialogView("Tour successfully created!", "Create Tour").show();
            feedbackText.setText("Tour successfully created!");

            ListToursView.getInstance().clearItems();
            ListToursView.getInstance().initList();

            resetTextfields();
        }
        catch (BadRequestException e) {
            logger.warn("BusinessLogic.createTour() - " + e.getMessage());
            new AboutDialogView("Bad Request\nTour could not be created!", "Create Tour").show();
            resetTextfields();
        } catch (InternalServerErrorException e) {
            logger.error("BusinessLogic.createTour() - " + e.getMessage());
            new AboutDialogView("Internal Server Issues\nTour could not be created!", "Create Tour").show();
            resetTextfields();
        } catch (FailedToSendRequestException e) {
            logger.error("BusinessLogic.createTour() - " + e.getMessage());
            new AboutDialogView("Failed to send Request\nTour could not be created!", "Create Tour").show();
            resetTextfields();
        } catch (FailedToParseImageFileException e) {
            logger.error("BusinessLogic.createTour() - " + e.getMessage());
            new AboutDialogView("Failed to parse image\nTour could not be created!", "Create Tour").show();
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
