package at.fhtw.viewmodel;

import at.fhtw.exceptions.*;
import at.fhtw.models.TourListEntry;
import at.fhtw.services.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
        } catch (BadRequestException | FailedToSendRequestException | NoContentException | NotFoundException e) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------------------------------------------");
            // TODO: handle exceptions properly
        }
    }

    public void filterList(String keyword){

        try {
            tourService.searchTour(keyword).forEach(p -> {
                addItem(p);
            });
            logger.info("BusinessLogic.searchTour() - tourList retrieved successfully: " + tourListItems);
            // TODO: show the list in ui
        } catch (NoContentException e) {
            logger.info("BusinessLogic.searchTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (NotFoundException e) {
            logger.info("BusinessLogic.searchTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (InternalServerErrorException e) {
            logger.error("BusinessLogic.searchTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (FailedToSendRequestException e) {
            logger.error("BusinessLogic.searchTour() - " + e.getMessage());
            // TODO: handle exception properly
        }



    }

    public void deleteTour(Integer tourId)
    {
        try {
            tourService.deleteTour(tourId);
            logger.info("BusinessLogic.deleteTour() - tour deleted successfully: " + tourId);
            // TODO: remove the tour from ui
        } catch (NotFoundException e) {
            logger.info("BusinessLogic.deleteTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (InternalServerErrorException e) {
            logger.error("BusinessLogic.deleteTour() - " + e.getMessage());
            // TODO: handle exception properly
        } catch (FailedToSendRequestException e) {
            logger.error("BusinessLogic.deleteTour() - " + e.getMessage());
            // TODO: handle exception properly
        }
    }
}
