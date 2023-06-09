package at.fhtw.view;

import at.fhtw.viewmodel.ShowTourInformationViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowTourInformationView implements Initializable {
    private static ShowTourInformationViewModel showTourInformationViewModel;
    @FXML
    private Label tourNameLabel;
    @FXML
    private Label startLabel;
    @FXML
    private Label destinationLabel;
    @FXML
    private Label transportTypeLabel;
    @FXML
    private Label distanceLabel;
    @FXML
    private Label estimatedTimeLabel;
    @FXML
    private Label popularityLabel;
    @FXML
    private Label childFriendlinessLabel;
    @FXML
    private Label tourDescriptionLabel;
    @FXML
    private Boolean showInformation;
    @FXML
    private Boolean hideInformation;

    @FXML
    private HBox HBoxTourInformation;
    @FXML
    private HBox HBoxImage;
    @FXML
    private ImageView ImageView;
    @FXML
    private Pane ImagePane;
    @FXML
    private VBox informationTypeField;
    @FXML
    private Label informationTextField;
    @FXML
    private static ImageView imageView = new ImageView();

    public ShowTourInformationView()
    {
        this.showTourInformationViewModel = new ShowTourInformationViewModel();
    }

    public static ShowTourInformationViewModel getInstance()
    {
        if(showTourInformationViewModel == null)
        {
            showTourInformationViewModel = new ShowTourInformationViewModel();
        }
        return showTourInformationViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle rb){

        imageView.imageProperty().bindBidirectional(showTourInformationViewModel.imageProperty());
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        HBoxImage.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        HBoxImage.getChildren().add(imageView);

        tourNameLabel.textProperty().bindBidirectional(showTourInformationViewModel.tourNameProperty());
        startLabel.textProperty().bindBidirectional(showTourInformationViewModel.startProperty());
        destinationLabel.textProperty().bindBidirectional(showTourInformationViewModel.destinationProperty());
        transportTypeLabel.textProperty().bindBidirectional(showTourInformationViewModel.transportTypeProperty());
        distanceLabel.textProperty().bindBidirectional(showTourInformationViewModel.distanceProperty());
        estimatedTimeLabel.textProperty().bindBidirectional(showTourInformationViewModel.estimatedTimeProperty());
        popularityLabel.textProperty().bindBidirectional(showTourInformationViewModel.popularityProperty());
        childFriendlinessLabel.textProperty().bindBidirectional(showTourInformationViewModel.childFriendlinessProperty());
        tourDescriptionLabel.textProperty().bindBidirectional(showTourInformationViewModel.tourDescriptionProperty());

        informationTypeField.visibleProperty().bindBidirectional(showTourInformationViewModel.showInformationProperty());
        informationTextField.visibleProperty().bindBidirectional(showTourInformationViewModel.hideInformationProperty());
    }
}
