package at.fhtw.client.view;

import at.fhtw.client.viewmodel.TourInformationViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TourInformationView implements Initializable {
    private static TourInformationViewModel tourInformationViewModel;
    @FXML
    private VBox imageContainer;
    @FXML
    private ImageView imageView;
    @FXML
    private Image image;
    private String imagePath;

    public TourInformationView()
    {
        this.tourInformationViewModel = new TourInformationViewModel();
    }

    public static TourInformationViewModel getInstance()
    {
        if(tourInformationViewModel == null)
        {
            tourInformationViewModel = new TourInformationViewModel();
        }
        return tourInformationViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle rb){
        /*textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                outputTextArea.appendText("TextField Text Changed (newValue: " + newValue + ")\n");
            }
        });*/
        imageView.imageProperty().bindBidirectional(tourInformationViewModel.getImage());
        //imagePath.textProperty().bindBidirectional(addTourViewModel.descriptionProperty());
        //String imagePath = tourInformationViewModel.getImagePath();



            Image image = new Image(tourInformationViewModel.getImagePath());
            ImageView imgView = new ImageView();
            imgView.setImage(image);
            //Rectangle2D viewportRect = new Rectangle2D(170, 80, 120, 300);
            //imgView.setViewport(viewportRect);

            imageContainer.getChildren().add(imgView);






        /*imageContainer.setTitle("ImageView Beispiel");
        imageContainer.setWidth(imgView.getViewport().getWidth());
        imageContainer.setHeight(imgView.getViewport().getHeight());
        imageContainer.setScene(scene);
        imageContainer.show(); */


        /*tableView.setItems(listToursViewModel.getTourListItems());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory("tourId"));
        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        tableView.getColumns().addAll(id, name);

        dataContainer.getChildren().add(tableView);
        listToursViewModel.initList();


        //tableView.setOnAction(event -> loadData());
        tableView.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        tableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if(tableView.getSelectionModel().getSelectedItem() != null) {
                    showInformations((TourListEntry) tableView.getSelectionModel().getSelectedItem());
                }
            }
        });
        //searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        //  searchLabel.setText(newValue);
        //});*/
    }
}
