package at.fhtw.viewmodel;

import at.fhtw.exceptions.*;
import at.fhtw.models.Tour;
import at.fhtw.models.TourListEntry;
import at.fhtw.services.TourService;
import at.fhtw.utils.ImageHandler;
import at.fhtw.view.ShowTourInformationView;
import at.fhtw.view.ShowTourLogsView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ListToursViewModel {
    private static final Logger logger = LogManager.getLogger(ListToursViewModel.class);
    private static ListToursViewModel instance;
    private final TourService tourService;
    private List<TourListEntry> masterData;
    private ObservableList<TourListEntry> tourListItems;

    public ListToursViewModel()
    {
        this.tourService = new TourService();
        masterData = new ArrayList<>();
        tourListItems = FXCollections.observableArrayList();
    }

    public static ListToursViewModel getInstance()
    {
        if(instance == null)
        {
            instance = new ListToursViewModel();
        }
        return instance;
    }

    public List<TourListEntry> getMasterDataListItems() {
        return masterData;
    }
    public ObservableList<TourListEntry> getTourListItems() {
        return tourListItems;
    }

    public void addItem(TourListEntry tour) {
        tourListItems.add(tour);
        masterData.add(tour);
    }
    public void clearItems(){ tourListItems.clear(); }

    public void initList(){
        try {
            tourService.getTourList().forEach(p -> {
                addItem(p);
            });
            tourListItems.sort(Comparator.comparingInt(TourListEntry::getTourId));
            logger.info("ListToursViewModel.getTourList() - tourList retrieved successfully: " + tourListItems);
        } catch (NoContentException e) {
            throw new NoContentException(e);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e);
        } catch (FailedToSendRequestException e) {
            throw new FailedToSendRequestException(e);
        }
    }

    public Tour getTour(Integer tourId){
            try {
                Tour tour = tourService.getTour(tourId);
                ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
                logger.info("ListToursViewModel.getTour() - tour retrieved successfully: " + tour);
                return tour;
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

    public void filterList(String keyword){
        try {
            tourService.searchTour(keyword).forEach(p -> {
                addItem(p);
            });
            ShowTourInformationView.getInstance().hideInformation();
            ShowTourLogsView.getInstance().hideTourLogs();
            logger.info("ListToursViewModel.searchTour() - tourList retrieved successfully: " + tourListItems);
        } catch (NoContentException e) {
            throw new NoContentException(e);
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e);
        } catch (FailedToSendRequestException e) {
            throw new FailedToSendRequestException(e);
        }
    }

    public void addTour(Tour createdTour) {
        try {
            Tour tour = new TourService().createTour(createdTour);
            ShowTourInformationView.getInstance().hideInformation();
            ShowTourLogsView.getInstance().hideTourLogs();
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            logger.info("ListToursViewModel.createTour() - tour created successfully: " + tour);
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
    public void updateTour(Tour updatedTour, Integer tourId) {
        try {
            Tour tour = new TourService().updateTour(tourId, updatedTour);
            ShowTourInformationView.getInstance().hideInformation();
            ShowTourLogsView.getInstance().hideTourLogs();
            ImageHandler.saveBase64EncodedImageToFile(tour.getTourInformation(), tour.getTourId().toString());
            logger.info("ListToursViewModel.updateTour() - tour updated successfully: " + tour);
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
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


    public void deleteTour(Integer tourId)
    {
        try {
            tourService.deleteTour(tourId);
            ShowTourInformationView.getInstance().hideInformation();
            ShowTourLogsView.getInstance().hideTourLogs();
            logger.info("ListToursViewModel.deleteTour() - tour deleted successfully: " + tourId);
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e);
        } catch (FailedToSendRequestException e) {
            throw new FailedToSendRequestException(e);
        }
    }
}
