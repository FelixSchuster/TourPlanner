package at.fhtw.viewmodel;

import at.fhtw.businessLogic.BusinessLogic;
import at.fhtw.exceptions.*;
import at.fhtw.models.Tour;
import at.fhtw.services.TourService;
import at.fhtw.utils.ImageHandler;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddTourViewModel {
    private static final Logger logger = LogManager.getLogger(BusinessLogic.class);
    private SimpleStringProperty tourName = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty start = new SimpleStringProperty();
    private SimpleStringProperty destination = new SimpleStringProperty();
    private SimpleStringProperty transportType = new SimpleStringProperty();
    ObjectProperty<String> selectedTransportTypeOption = new SimpleObjectProperty<>();


    public AddTourViewModel() {
    }

    public AddTourViewModel(Tour tour) {
        //this.tour3 = tour;
        this.tourName = new SimpleStringProperty(tour.getName());
        this.description = new SimpleStringProperty(tour.getTourDescription());
        this.start = new SimpleStringProperty(tour.getStart());
        this.destination = new SimpleStringProperty(tour.getDestination());
        this.transportType = new SimpleStringProperty(tour.getTransportType());
    }

    public String getTourName() {
        return tourName.get();
    }

    public SimpleStringProperty tourNameProperty() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName.set(tourName);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getStart() {
        return start.get();
    }

    public SimpleStringProperty startProperty() {
        return start;
    }

    public void setStart(String start) {
        this.start.set(start);
    }

    public String getDestination() {
        return destination.get();
    }

    public SimpleStringProperty destinationProperty() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public String getTransportType() {
        return transportType.get();
    }

    public SimpleStringProperty transportTypeProperty() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType.set(transportType);
    }

    public String getSelectedTransportTypeOption() {
        return selectedTransportTypeOption.get();
    }

    public ObjectProperty<String> selectedTransportTypeOptionProperty() {
        return selectedTransportTypeOption;
    }

    public void setSelectedTransportTypeOption(String selectedTransportTypeOption) {
        this.selectedTransportTypeOption.set(selectedTransportTypeOption);
    }

    public void addTour() {
        /*try {
            Tour createTour = new Tour(tourName.get(), description.get(), start.get(), destination.get(), transportType.get());
            Tour tour = new TourService().createTour(createTour);
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            // TODO: show created tour in ui, also show the image (filename is <tourId>.jpg)
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException |
                 FailedToParseImageFileException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }*/

        try {
            Tour createTour = new Tour(tourName.get(), description.get(), start.get(), destination.get(), transportType.get());
            Tour tour = new TourService().createTour(createTour);
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            logger.info("BusinessLogic.createTour() - tour created successfully: " + tour);
            // TODO: show created tour in ui, also show the image (filename is data/images/<tourId>.jpg)
        } catch (BadRequestException e) {
            logger.warn("BusinessLogic.createTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (InternalServerErrorException e) {
            logger.error("BusinessLogic.createTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (FailedToSendRequestException e) {
            logger.error("BusinessLogic.createTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (FailedToParseImageFileException e) {
            logger.error("BusinessLogic.createTour() - " + e.getMessage());
            // TODO: handle exception properly
        }
    }
}
