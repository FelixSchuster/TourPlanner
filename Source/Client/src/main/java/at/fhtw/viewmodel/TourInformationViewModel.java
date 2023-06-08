package at.fhtw.viewmodel;

import at.fhtw.models.TourListEntry;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class TourInformationViewModel {
    private String imagePath;
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    private String imageFolderPath = "file:data/images/";

    public TourInformationViewModel()
    {
        Image img = new Image("file:data/images/1.jpg");
        String imageName = "1.jpg";
        imagePath = imageFolderPath + imageName;
        image = new SimpleObjectProperty(new Image(imagePath));
    }

    public String getImagePath() {
        return imagePath;
    }

    public ObjectProperty getImageProperty() { return image; }

    public Image getImage() { return image.get(); }



    public void changeTourImagePath(TourListEntry tourListEntry)
    {
        String imageName = tourListEntry.getTourId() + ".jpg";

        imagePath = imageFolderPath + imageName;
        System.out.println("imagename"+imageName);
        System.out.println("imagepath"+imagePath);
        image = new SimpleObjectProperty(new Image(imagePath));
        System.out.println("new image: " + image.getValue());
    }
}
