package at.fhtw.viewmodel;

import at.fhtw.models.TourListEntry;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class TourInformationViewModel {
    private String imagePath;
    //private ObjectProperty<String> selectedTransportTypeOption = new SimpleObjectProperty<>();
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    private String imageFolderPath = "file:data/images/";

    public TourInformationViewModel()
    {
        /*Image img = new Image("file:data/images/1.jpg");
        String imageName = "4.jpg";
        imagePath = imageFolderPath + imageName;
        image = new SimpleObjectProperty(new Image(imagePath));*/
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }

    public Image getImage() { return image.get(); }



    public void changeTourImagePath(TourListEntry tourListEntry)
    {
        String imageName = (tourListEntry.getTourId()) + ".jpg";

        imagePath = imageFolderPath + imageName;
        System.out.println("imagename "+imageName);
        System.out.println("imagepath "+imagePath);

        image.set(new Image(imagePath));

    }
}
