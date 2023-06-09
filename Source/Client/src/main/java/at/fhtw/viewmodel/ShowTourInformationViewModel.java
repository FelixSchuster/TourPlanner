package at.fhtw.viewmodel;

import at.fhtw.exceptions.FailedToParseImageFileException;
import at.fhtw.exceptions.FailedToSendRequestException;
import at.fhtw.exceptions.InternalServerErrorException;
import at.fhtw.exceptions.NotFoundException;
import at.fhtw.models.Tour;
import at.fhtw.models.TourListEntry;
import at.fhtw.services.TourService;
import at.fhtw.utils.ImageHandler;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.util.StringConverter;

public class ShowTourInformationViewModel {
    private static final Logger logger = LogManager.getLogger(ShowTourInformationViewModel.class);
    private TourService tourService;
    private String imagePath;
    //private ObjectProperty<String> selectedTransportTypeOption = new SimpleObjectProperty<>();
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    private String imageFolderPath = "file:data/images/";
    private SimpleStringProperty tourName = new SimpleStringProperty();

    private SimpleStringProperty start = new SimpleStringProperty();

    private SimpleStringProperty destination = new SimpleStringProperty();

    private SimpleStringProperty transportType = new SimpleStringProperty();

    private SimpleStringProperty distance = new SimpleStringProperty();

    private SimpleStringProperty estimatedTime = new SimpleStringProperty();

    private SimpleStringProperty popularity = new SimpleStringProperty();

    private SimpleStringProperty childFriendliness = new SimpleStringProperty();
    private SimpleStringProperty tourDescription = new SimpleStringProperty();

    private SimpleBooleanProperty showInformation = new SimpleBooleanProperty();
    private SimpleBooleanProperty hideInformation = new SimpleBooleanProperty();

    public ShowTourInformationViewModel()
    {
        hideInformation();
        tourService = new TourService();
    }
    public ObjectProperty<Image> imageProperty() {
        return image;
    }
    public Image getImage() { return image.get(); }

    public void hideInformation()
    {
        showInformation.set(false);
        hideInformation.set(true);
    }

    public void showInformation()
    {
        showInformation.set(true);
        hideInformation.set(false);
    }

    public void changeTourImagePath(TourListEntry tourListEntry)
    {
        String imageName = (tourListEntry.getTourId()) + ".jpg";
        imagePath = imageFolderPath + imageName;
        image.set(new Image(imagePath));
    }

    public void changeTourInformation(TourListEntry tourListEntry)
    {
        try{
            String imageName = (tourListEntry.getTourId()) + ".jpg";
            imagePath = imageFolderPath + imageName;
            image.set(new Image(imagePath));


            Tour tour = tourService.getTour(tourListEntry.getTourId());
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            logger.info("ShowTourInformationViewModel.getTour() - tour retrieved successfully: " + tour);
            System.out.println("name: " + tour.getName());
            setTourInformation(tour);
            showInformation();

        } catch (NotFoundException e) {
            logger.info("BusinessLogic.getTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (InternalServerErrorException e) {
            logger.error("BusinessLogic.getTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (FailedToParseImageFileException e) {
            logger.error("BusinessLogic.getTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (FailedToSendRequestException e) {
            logger.error("BusinessLogic.getTour() - " + e.getMessage());
            // TODO: handle exception properly
        }
    }

    private void setTourInformation(Tour tour)
    {
        tourName.set(tour.getName());
        start.set(tour.getStart());
        destination.set(tour.getDestination());
        transportType.set(tour.getTransportType());
        distance.set(Double.toString(tour.getTourDistance()));
        estimatedTime.set(Integer.toString(tour.getEstimatedTime()));
        popularity.set(Integer.toString(tour.getPopularity()));
        childFriendliness.set(Integer.toString(tour.getChildFriendliness()));
        tourDescription.set(tour.getTourDescription());
    }

    public String getTourName() {
        return tourName.get();
    }

    public SimpleStringProperty tourNameProperty() {
        return tourName;
    }

    public String getStart() {
        return start.get();
    }

    public SimpleStringProperty startProperty() {
        return start;
    }

    public String getDestination() {
        return destination.get();
    }

    public SimpleStringProperty destinationProperty() {
        return destination;
    }

    public String getTransportType() {
        return transportType.get();
    }

    public SimpleStringProperty transportTypeProperty() {
        return transportType;
    }

    public String getDistance() {
        return distance.get();
    }

    public SimpleStringProperty distanceProperty() {
        return distance;
    }

    public String getEstimatedTime() {
        return estimatedTime.get();
    }

    public SimpleStringProperty estimatedTimeProperty() {
        return estimatedTime;
    }

    public String getPopularity() {
        return popularity.get();
    }

    public SimpleStringProperty popularityProperty() {
        return popularity;
    }

    public String getChildFriendliness() {
        return childFriendliness.get();
    }

    public SimpleStringProperty childFriendlinessProperty() {
        return childFriendliness;
    }

    public String getTourDescription() {
        return tourDescription.get();
    }

    public SimpleStringProperty tourDescriptionProperty() {
        return tourDescription;
    }

    public boolean isShowInformation() {
        return showInformation.get();
    }

    public SimpleBooleanProperty showInformationProperty() {
        return showInformation;
    }

    public boolean isHideInformation() {
        return hideInformation.get();
    }

    public SimpleBooleanProperty hideInformationProperty() {
        return hideInformation;
    }
}
