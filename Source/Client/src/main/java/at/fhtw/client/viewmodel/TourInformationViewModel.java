package at.fhtw.client.viewmodel;

import at.fhtw.client.models.TourListEntry;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class TourInformationViewModel {
    private String imagePath;
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    private String imageFolderPath = "C:\\Users\\Bea\\Documents\\FH_UNI\\FH Technikum\\4.Semester\\SWEN2_Software_Engineering\\TourPlanner_v2\\TourPlanner\\Source\\Client\\";

    public String getImagePath() {
        return imagePath;
    }

    public ObjectProperty getImage() { return image; }

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
