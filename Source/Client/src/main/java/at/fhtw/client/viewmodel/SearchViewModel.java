package at.fhtw.client.viewmodel;

import at.fhtw.client.models.TourListEntry;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SearchViewModel {

    private ListToursViewModel toursViewModel = new ListToursViewModel();

    private SimpleStringProperty searchString = new SimpleStringProperty();


    public String getSearchString() {
        return searchString.get();
    }

    public SimpleStringProperty searchStringProperty() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString.set(searchString);
    }

    public void search() {
        toursViewModel.initList();
        System.out.println("search1");

        System.out.println("search2");

        toursViewModel.filterList(getSearchString());
        System.out.println("search3");

        System.out.print("search text: ");
        System.out.println(getSearchString());

        ObservableList<TourListEntry> list = toursViewModel.getTourListItems();
        for (TourListEntry value : list)
        {
            System.out.print("value: ");
            System.out.println(value);
        }
        System.out.println("search4");

    }
}
