package at.fhtw.viewmodel;

import at.fhtw.exceptions.FailedToSendRequestException;
import at.fhtw.exceptions.InternalServerErrorException;
import at.fhtw.exceptions.NoContentException;
import at.fhtw.exceptions.NotFoundException;
import at.fhtw.models.TourListEntry;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SearchViewModel {
    private static final Logger logger = LogManager.getLogger(SearchViewModel.class);

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
    }
}
