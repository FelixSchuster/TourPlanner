package at.fhtw.client.viewmodel;

import at.fhtw.client.models.TourListEntry;
import at.fhtw.client.view.ListToursView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class ReloadToursViewModel {

    private ListToursViewModel toursViewModel = new ListToursViewModel();
    //private ListToursView toursView = new ListToursView();

    public void reload() {
        toursViewModel.clearItems();
        toursViewModel.initList();
        System.out.println("list: ");
        System.out.println(toursViewModel.getTourListItems());
    }
}
