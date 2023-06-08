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
    private static final Logger logger = LogManager.getLogger(AddTourViewModel.class);
    private SimpleStringProperty tourName = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty start = new SimpleStringProperty();
    private SimpleStringProperty destination = new SimpleStringProperty();
    private ObjectProperty<String> selectedTransportTypeOption = new SimpleObjectProperty<>();


    public AddTourViewModel() {
    }

    public AddTourViewModel(Tour tour) {
        //this.tour3 = tour;
        /*this.tourName = new SimpleStringProperty(tour.getName());
        this.description = new SimpleStringProperty(tour.getTourDescription());
        this.start = new SimpleStringProperty(tour.getStart());
        this.destination = new SimpleStringProperty(tour.getDestination());
        this.transportType = new SimpleStringProperty(tour.getTransportType());*/
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
        try {
            System.out.print("option: ");
            System.out.println(selectedTransportTypeOption.get());
            Tour createTour = new Tour(tourName.get(), description.get(), start.get(), destination.get(), selectedTransportTypeOption.get());
            Tour tour = new TourService().createTour(createTour);
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            logger.info("BusinessLogic.createTour() - tour created successfully: " + tour);
            // TODO: show created tour in ui, also show the image (filename is data/images/<tourId>.jpg)
        } catch (BadRequestException e) {
            throw new BadRequestException(e);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e);
        } catch (FailedToSendRequestException e) {
            throw new FailedToSendRequestException(e);
        } catch (FailedToParseImageFileException e) {
            throw new FailedToParseImageFileException(e);
        }
    }
}
