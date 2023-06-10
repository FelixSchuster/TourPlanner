package at.fhtw.viewmodel;

import at.fhtw.exceptions.*;
import at.fhtw.models.Tour;
import at.fhtw.models.TourListEntry;
import at.fhtw.services.TourService;
import at.fhtw.utils.ImageHandler;
import at.fhtw.utils.pdfFileHandler.PdfFileHandler;
import at.fhtw.view.ListToursView;
import at.fhtw.view.popUps.DialogView;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private TourListEntry tourListEntry;

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

    public void changeTourInformation(TourListEntry tourListEntry)
    {
        try{
            this.tourListEntry = tourListEntry;
            String imageName = (tourListEntry.getTourId()) + ".jpg";
            imagePath = imageFolderPath + imageName;
            image.set(new Image(imagePath));

            Tour tour = ListToursView.getInstance().getTour(tourListEntry.getTourId());
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            logger.info("ShowTourInformationViewModel.getTour() - tour retrieved successfully: " + tour);
            setTourInformation(tour);
            showInformation();

        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e);
        } catch (FailedToParseImageFileException e) {
            throw new FailedToParseImageFileException(e);
        } catch (FailedToSendRequestException e) {
            throw new FailedToSendRequestException(e);
        }
    }

    private void setTourInformation(Tour tour)
    {
        if((tour.getEstimatedTime()/60) >= 1)
        {
            int hours = (tour.getEstimatedTime()/60);
            int minutes = ((tour.getEstimatedTime()) - (60 * hours));
            estimatedTime.set(Integer.toString(hours) + "h " + Integer.toString(minutes) + "min");
        }
        else
        {
            estimatedTime.set((Integer.toString(tour.getEstimatedTime()) + "min"));
        }
        tourName.set(tour.getName());
        start.set(tour.getStart());
        destination.set(tour.getDestination());
        transportType.set(tour.getTransportType());
        distance.set(Double.toString(tour.getTourDistance()) + "km");
        popularity.set(Integer.toString(tour.getPopularity()));
        childFriendliness.set(Integer.toString(tour.getChildFriendliness()));
        tourDescription.set(tour.getTourDescription());
    }

    public void createTourReport(Integer tourId, String filename) {
        try {
            PdfFileHandler pdfFileHandler = new PdfFileHandler();
            Tour tour = tourService.getTour(tourId);
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tourId.toString());
            pdfFileHandler.createTourReport(tour, filename);
            logger.info("ShowTourInformationViewModel.createTourReport() - report created successfully: " + filename);
            new DialogView("Tour Report successfully created", "Create Report Tour");
        } catch (NoContentException e) {
            logger.info("ShowTourInformationViewModel.createTourReport() - " + e.getMessage());
            new DialogView("Tour has no content", "Create Report Tour");
        } catch (NotFoundException e) {
            logger.info("ShowTourInformationViewModel.createTourReport() - " + e.getMessage());
            new DialogView("Tour could not be found", "Create Report Tour");
        } catch (InternalServerErrorException e) {
            logger.error("ShowTourInformationViewModel.createTourReport() - " + e.getMessage());
            new DialogView("Internal Server Issues\nThe Tour Tour Report could not be created!", "Create Report Tour");
        } catch (FailedToParseJsonFileException e) {
            logger.error("ShowTourInformationViewModel.createTourReport() - " + e.getMessage());
            new DialogView("Failed to parse jsonfile\nThe Tour Report could not be created!", "Create Report Tour");
        } catch (FailedToCreatePdfFileException e) {
            logger.error("ShowTourInformationViewModel.createTourReport() - " + e.getMessage());
            new DialogView("Failed to parse image\nThe Tour Report could not be created!", "Create Report Tour");
        } catch (FailedToSendRequestException e) {
            logger.error("ShowTourInformationViewModel.createTourReport() - " + e.getMessage());
            new DialogView("Failed to send Request\nThe Tour Report could not be created!", "Create Report Tour");
        }
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

    public TourListEntry getTourListEntry() {
        return tourListEntry;
    }
}
