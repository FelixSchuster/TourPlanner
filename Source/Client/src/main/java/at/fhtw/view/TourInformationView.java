package at.fhtw.view;

import at.fhtw.viewmodel.TourInformationViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TourInformationView implements Initializable {
    private static TourInformationViewModel tourInformationViewModel;
    @FXML
    private HBox HBoxImage;
    @FXML
    private static ImageView imageView = new ImageView();

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

    public static void updateTourinformation()
    {
        imageView.setImage(tourInformationViewModel.getImage());
    }

    @Override
    public void initialize(URL location, ResourceBundle rb){

        //imageView.setImage(tourInformationViewModel.getImage());
        imageView.imageProperty().bindBidirectional(tourInformationViewModel.imageProperty());
        HBoxImage.getChildren().add(imageView);
        //imageView.setImage(tourInformationViewModel.getImage());


    /*

            Image image = new Image(tourInformationViewModel.getImagePath());
            ImageView imgView = new ImageView();
            imgView.setImage(image);
            //Rectangle2D viewportRect = new Rectangle2D(170, 80, 120, 300);
            //imgView.setViewport(viewportRect);

            imageContainer.getChildren().add(imgView);


*/
    }
}
