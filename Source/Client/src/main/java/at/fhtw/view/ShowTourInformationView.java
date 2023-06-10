package at.fhtw.view;

import at.fhtw.view.popUps.UpdateTourPopUpView;
import at.fhtw.viewmodel.ListToursViewModel;
import at.fhtw.viewmodel.ShowTourInformationViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowTourInformationView implements Initializable {
    private static final Logger logger = LogManager.getLogger(ShowTourInformationView.class);
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
    private HBox HBoxImage;
    @FXML
    private VBox informationTypeField;
    @FXML
    private Label informationTextField;
    @FXML
    public Button updateButton;
    @FXML
    public Button createTourReportButton;
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
        imageView.setFitWidth(280);
        imageView.setFitHeight(280);
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
        tourDescriptionLabel.setWrapText(true);
        estimatedTimeLabel.setWrapText(true);

        informationTypeField.visibleProperty().bindBidirectional(showTourInformationViewModel.showInformationProperty());
        informationTextField.visibleProperty().bindBidirectional(showTourInformationViewModel.hideInformationProperty());
        updateButton.setOnAction(event -> updateTour());
        createTourReportButton.setOnAction(event -> onActionCreateTourReport());
    }

    public void updateTour()
    {
        ListToursViewModel listToursViewModel = ListToursView.getInstance();
        new UpdateTourPopUpView(listToursViewModel.getTour(showTourInformationViewModel.getTourListEntry().getTourId()), "Update Tour");
    }

    public void onActionCreateTourReport()
    {
        String filename = Integer.toString(showTourInformationViewModel.getTourListEntry().getTourId()) + "_" + showTourInformationViewModel.getTourListEntry().getName() + "_" + "report";
        showTourInformationViewModel.createTourReport(showTourInformationViewModel.getTourListEntry().getTourId(), filename);
    }
}
