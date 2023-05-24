package at.fhtw.client.viewmodel;

import at.fhtw.client.models.TourListEntry;
import at.fhtw.client.services.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ListToursViewModel {

    public TourService tourService = new TourService();
    private List<TourListEntry> masterData = new ArrayList<>();
    private ObservableList<TourListEntry> tourListItems = FXCollections.observableArrayList();

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

        tourService.getTourList().forEach(p -> {
            addItem(p);
        });
    }

    public void filterList(String searchText){
        Task<List<TourListEntry>> task = new Task<>() {
            @Override
            protected List<TourListEntry> call() throws Exception {
                updateMessage("Loading data");
                return masterData
                        .stream()
                        .filter(value -> value.getName().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
            }
        };

        task.setOnSucceeded(event -> {
            tourListItems.setAll(task.getValue());
        });

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
}
