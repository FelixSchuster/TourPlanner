package at.fhtw.businessLogic;

import at.fhtw.exceptions.*;
import at.fhtw.models.Tour;
import at.fhtw.models.TourListEntry;
import at.fhtw.models.TourLog;
import at.fhtw.utils.ImageHandler;
import at.fhtw.utils.RequestSender;

import java.util.List;

public class BusinessLogic {
    private static final RequestSender requestSender = new RequestSender();
    public void createTour(Tour tour) {
        try {
            tour = requestSender.createTour(tour);
            System.out.println(tour);
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            // TODO: show created tour in ui, also show the image (filename is <tourId>.jpg)
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException | FailedToParseImageFileException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void getTourList() {
        try {
            List<TourListEntry> tourList = requestSender.getTourList();
            System.out.println(tourList);
            // TODO: show the list in ui
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void getTour(Integer tourId) {
        try {
            Tour tour = requestSender.getTour(tourId);
            System.out.println(tour);
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            // TODO: show the tour in ui, also show the image (filename is <tourId>.jpg)
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException | FailedToParseImageFileException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void updateTour(Integer tourId, Tour tour) {
        try {
            tour = requestSender.updateTour(tourId, tour);
            System.out.println(tour);
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            // TODO: show the updated tour in ui, also show the updated image (filename is <tourId>.jpg)
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException | FailedToParseImageFileException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void deleteTour(Integer tourId) {
        try {
            requestSender.deleteTour(tourId);
            System.out.println("tour removed - tourId: " + tourId);
            // TODO: remove the tour from ui
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void createTourLog(Integer tourId, TourLog tourLog) {
        try {
            tourLog = requestSender.createTourLog(tourId, tourLog);
            System.out.println(tourLog);
            // TODO: show created tourLog in ui
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void getTourLogs(Integer tourId) {
        try {
            List<TourLog> tourLogs= requestSender.getTourLogs(tourId);
            System.out.println(tourLogs);
            // TODO: show tourLogs in ui
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void getTourLog(Integer tourLogId) {
        try {
            TourLog tourLog= requestSender.getTourLog(tourLogId);
            System.out.println(tourLog);
            // TODO: show tourLog in ui
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void updateTourLog(Integer tourLogId, TourLog tourLog) {
        try {
            tourLog = requestSender.updateTourLog(tourLogId, tourLog);
            System.out.println(tourLog);
            // TODO: show updated tourLog in ui
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void deleteTourLog(Integer tourLogId) {
        try {
            requestSender.deleteTourLog(tourLogId);
            System.out.println("tourLog removed - tourLogId: " + tourLogId);
            // TODO: remove the tourLog from ui
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void exportTours(String filename) {
        try {
            List<Tour> tours = requestSender.exportTours(filename);
            System.out.println(tours);
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException | FailedToParseJsonFileException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void importTours(String filename) {
        try {
            List<Tour> tours = requestSender.importTours(filename);
            System.out.println(tours);
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException |
                 FailedToParseJsonFileException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
}
