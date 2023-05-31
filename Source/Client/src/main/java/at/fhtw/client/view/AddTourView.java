package at.fhtw.client.view;

import at.fhtw.client.models.TourListEntry;
import at.fhtw.client.viewmodel.AddTourViewModel;
import at.fhtw.client.viewmodel.MainViewModel;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTourView implements Initializable {



    private AddTourViewModel addTourViewModel = new AddTourViewModel();
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
    private TextField transportTypeTextField;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        tourNameTextField.textProperty().bindBidirectional(addTourViewModel.tourNameProperty());
        descriptionTextField.textProperty().bindBidirectional(addTourViewModel.descriptionProperty());
        startTextField.textProperty().bindBidirectional(addTourViewModel.startProperty());
        destinationTextField.textProperty().bindBidirectional(addTourViewModel.destinationProperty());
        transportTypeTextField.textProperty().bindBidirectional(addTourViewModel.transportTypeProperty());
    }

    public void addTourAction(ActionEvent event) {
        if (startTextField.getText().isEmpty()) {
            feedbackText.setText("nothing entered!");
            return;
        }
        else if (destinationTextField.getText().isEmpty()) {
            feedbackText.setText("nothing entered!");
            return;
        }
        else if (transportTypeTextField.getText().isEmpty()) {
            feedbackText.setText("nothing entered!");
            return;
        }

        addTourViewModel.addTour();
        //ListToursView.getInstance().addItem(new TourListEntry());
    }
}
