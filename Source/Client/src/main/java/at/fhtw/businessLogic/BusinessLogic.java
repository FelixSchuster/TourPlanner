package at.fhtw.businessLogic;

import at.fhtw.exceptions.*;
import at.fhtw.models.Tour;
import at.fhtw.models.TourListEntry;
import at.fhtw.models.TourLog;
import at.fhtw.services.DataTransferService;
import at.fhtw.services.TourLogService;
import at.fhtw.services.TourService;
import at.fhtw.utils.ImageHandler;
import at.fhtw.utils.pdfFileHandler.PdfFileHandler;

import java.util.List;

public class BusinessLogic {
    private TourService tourService = new TourService();
    private TourLogService tourLogService = new TourLogService();
    private DataTransferService dataTransferService = new DataTransferService();
    public void createTour(Tour tour) {
        try {
            tour = tourService.createTour(tour);
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
            List<TourListEntry> tourList = tourService.getTourList();
            System.out.println(tourList);
            // TODO: show the list in ui
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void searchTour(String keyword) {
        try {
            List<TourListEntry> tourList = tourService.searchTour(keyword);
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
            Tour tour = tourService.getTour(tourId);
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
            tour = tourService.updateTour(tourId, tour);
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
            tourService.deleteTour(tourId);
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
            tourLog = tourLogService.createTourLog(tourId, tourLog);
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
            List<TourLog> tourLogs= tourLogService.getTourLogs(tourId);
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
            TourLog tourLog= tourLogService.getTourLog(tourLogId);
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
            tourLog = tourLogService.updateTourLog(tourLogId, tourLog);
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
            tourLogService.deleteTourLog(tourLogId);
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
            List<Tour> tours = dataTransferService.exportTours(filename);
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
            List<Tour> tours = dataTransferService.importTours(filename);
            System.out.println(tours);
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException |
                 FailedToParseJsonFileException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void createSummarizeReport(String filename) {
        try {
            PdfFileHandler pdfFileHandler = new PdfFileHandler();
            List<Tour> tours = dataTransferService.exportTours(null);
            for(Tour tour : tours) {
                ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            }
            System.out.println(tours);
            pdfFileHandler.createSummarizeReport(tours, filename);
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException | FailedToParseJsonFileException | FailedToCreatePdfFileException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
    public void createTourReport(Integer tourId, String filename) {
        try {
            PdfFileHandler pdfFileHandler = new PdfFileHandler();
            Tour tour = tourService.getTour(tourId);
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tourId.toString());
            System.out.println(tour);
            pdfFileHandler.createTourReport(tour, filename);
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException | FailedToParseJsonFileException | FailedToCreatePdfFileException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }
}
