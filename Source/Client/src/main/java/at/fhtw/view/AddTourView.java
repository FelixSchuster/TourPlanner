package at.fhtw.view;

import at.fhtw.models.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTourView implements Initializable {
    private static final Logger logger = LogManager.getLogger(AddTourView.class);
    @FXML
    private Label feedback;
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
        feedback.getStyleClass().add("feedbackText");
        transportTypeChoiceBox.setValue("car");
        updateChoiceBoxWidth();
        transportTypeChoiceBox.setOnAction(event -> updateChoiceBoxWidth());
    }

    @FXML
    private void updateChoiceBoxWidth() {
        String selectedValue = transportTypeChoiceBox.getSelectionModel().getSelectedItem();
        double newWidth = (selectedValue.length() * 10) + 3;
        System.out.println("length: " + selectedValue.length());
        transportTypeChoiceBox.setPrefWidth(newWidth);
    }

    public void addTourAction(ActionEvent event) {

        if (tourNameTextField.getText() == null ||
                tourNameTextField.getText().isBlank() ||
                tourNameTextField.getText().isEmpty()) {
            feedback.setText("Please enter a tourname!");
            return;
        } else if (tourNameTextField.getText().length() > 20) {
            feedback.setText("Tourname is too long!");
            return;
        } else if (descriptionTextField.getText() == null ||
                descriptionTextField.getText().isBlank() ||
                descriptionTextField.getText().isEmpty()) {
            feedback.setText("Please enter a description!");
            return;
        } else if (startTextField.getText() == null ||
                startTextField.getText().isBlank() ||
                startTextField.getText().isEmpty()) {
            feedback.setText("Please enter a start!");
            return;
        } else if (destinationTextField.getText() == null ||
                destinationTextField.getText().isBlank() ||
                destinationTextField.getText().isEmpty()) {
            feedback.setText("Please enter a destination!");
            return;
        }

        Tour tour = new Tour(tourNameTextField.getText(), descriptionTextField.getText(), startTextField.getText(), destinationTextField.getText(), transportTypeChoiceBox.getSelectionModel().getSelectedItem());
        ListToursView.getInstance().addTour(tour);

        ListToursView.getInstance().clearItems();
        ListToursView.getInstance().initList();

        resetTextfields();
    }

    private void resetTextfields()
    {
        tourNameTextField.setText("");
        descriptionTextField.setText("");
        startTextField.setText("");
        destinationTextField.setText("");
        transportTypeChoiceBox.setValue("car");
        feedback.setText("");
    }
}
