package at.fhtw.client.viewmodel;

import at.fhtw.client.exceptions.*;
import at.fhtw.client.models.Tour;
import at.fhtw.client.services.TourService;
import at.fhtw.client.utils.ImageHandler;
import javafx.beans.property.*;
import org.springframework.beans.factory.annotation.Autowired;

public class AddTourViewModel {
    private SimpleStringProperty start = new SimpleStringProperty();
    private SimpleStringProperty destination = new SimpleStringProperty();
    private SimpleStringProperty transportType = new SimpleStringProperty();

    private Tour tour;


    public AddTourViewModel() {

    }

    public AddTourViewModel(Tour tour) {
        this.tour = tour;
        this.start = new SimpleStringProperty(tour.getStart());
        this.destination = new SimpleStringProperty(tour.getDestination());
        this.transportType = new SimpleStringProperty(tour.getTransportType());
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

    public void addTour() {
        try {
            Tour tour2 = new TourService().createTour(tour);
            System.out.println(tour2);
            ImageHandler.saveBase64EncodedImageToFile(tour2.getTourInformation(), tour2.getTourId().toString());
            // TODO: show created tour in ui, also show the image (filename is <tourId>.jpg)
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException |
                 FailedToParseImageFileException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
}
