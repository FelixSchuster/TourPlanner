package at.fhtw.client.viewmodel;

import at.fhtw.client.models.Person;
import at.fhtw.client.models.Tour;
import at.fhtw.client.models.TourListEntry;
import at.fhtw.client.services.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToursViewModel {

    public TourService tourService = new TourService();
    private List<Person> masterData = new ArrayList<>();
    private ObservableList<Person> tourListItems = FXCollections.observableArrayList();

    public List<Person> getMasterDataListItems() {
        return masterData;
    }
    public ObservableList<Person> getTourListItems() {
        return tourListItems;
    }

    public void addItem(Person tour) {
        tourListItems.add(tour);
        masterData.add(tour);
    }
    public void clearItems(){ tourListItems.clear(); }

    public void initList(){

        /*tourService.getTourList().forEach(p -> {
            addItem(p);
        });*/
        getItems().forEach(p -> {
            addItem(p);
        });
    }
    public List<Person> getItems() {

        Person[] tourItems =
                {
                        new Person("Item1"),
                        new Person("Item2"),
                        new Person("Another"),
                        new Person("SWE1"),
                        new Person("FHTW"),
                };
        return new ArrayList<Person>(Arrays.asList(tourItems));
    }
}
