package at.fhtw.view;

import at.fhtw.models.TourListEntry;
import at.fhtw.viewmodel.AddTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTourView implements Initializable {



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
    private TextField transportTypeTextField;
    @FXML
    private ChoiceBox<String> transportTypeChoiceBox;
    @FXML
    private Text hiddenText;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        //Tour tour = new Tour();


        tourNameTextField.setText("");
        descriptionTextField.setText("");
        startTextField.setText("");
        destinationTextField.setText("");
        transportTypeTextField.setText("");


        addTourViewModel = new AddTourViewModel();
        tourNameTextField.textProperty().bindBidirectional(addTourViewModel.tourNameProperty());
        descriptionTextField.textProperty().bindBidirectional(addTourViewModel.descriptionProperty());
        startTextField.textProperty().bindBidirectional(addTourViewModel.startProperty());
        destinationTextField.textProperty().bindBidirectional(addTourViewModel.destinationProperty());
        transportTypeTextField.textProperty().bindBidirectional(addTourViewModel.transportTypeProperty());
        transportTypeChoiceBox.valueProperty().bindBidirectional(addTourViewModel.selectedTransportTypeOptionProperty());

        transportTypeChoiceBox.setValue("car");
        updateChoiceBoxWidth();
        transportTypeChoiceBox.setOnAction(event -> updateChoiceBoxWidth());
    }

    @FXML
    private void updateChoiceBoxWidth() {
        String selectedValue = transportTypeChoiceBox.getSelectionModel().getSelectedItem();
        double newWidth = selectedValue.length() * 10; // Adjust the factor as needed
        System.out.println("length: " + selectedValue.length());
        transportTypeChoiceBox.setPrefWidth(newWidth);
    }

    public void addTourAction(ActionEvent event) {

        try {
            System.out.println("iam here 5");
            if (tourNameTextField.getText().trim().equals("")) {
                feedbackText.setText("nothing entered!");
                System.out.println("yeahh");
                return;
            } else if (descriptionTextField.getText().isEmpty()) {
                feedbackText.setText("nothing entered!");
                return;
            } else if (startTextField.getText().isEmpty()) {
                feedbackText.setText("nothing entered!");
                return;
            } else if (destinationTextField.getText().isEmpty()) {
                feedbackText.setText("nothing entered!");
                return;
            } else if (transportTypeTextField.getText().isEmpty()) {
                feedbackText.setText("nothing entered!");
                return;
            }

            addTourViewModel.addTour();
            System.out.println("iam here 6");
            feedbackText.setText("Tour successfully created!");
        }
        catch(NullPointerException e)
        {
            feedbackText.setText("nothing entered! exc");
            return;
        }

        ListToursView.getInstance().addItem(new TourListEntry());

        tourNameTextField.setText("");
        descriptionTextField.setText("");
        startTextField.setText("");
        destinationTextField.setText("");
        transportTypeTextField.setText("");

        //ListToursView.getInstance().addItem(new TourListEntry());
    }
}
