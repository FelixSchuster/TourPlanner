package at.fhtw.client.viewmodel;

import at.fhtw.client.models.TourListEntry;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

public class TourInformationsViewModel {
    private String imagePath;
    private String imageFolderPath = "C:\\Users\\Bea\\Documents\\FH_UNI\\FH Technikum\\4.Semester\\SWEN2_Software_Engineering\\TourPlanner_v2\\TourPlanner\\Source\\Client\\";

    public String getImagePath() {
        return imagePath;
    }

    public void changeTourImagePath(TourListEntry tourListEntry)
    {

        String imageName = tourListEntry.getTourId() + ".jpg";

        imagePath = imageFolderPath + imageName;
        System.out.println("imagename"+imageName);
        System.out.println("imagepath"+imagePath);
    }
}
