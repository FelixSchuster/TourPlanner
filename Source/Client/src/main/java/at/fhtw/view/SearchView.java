package at.fhtw.view;


import at.fhtw.models.TourListEntry;
import at.fhtw.viewmodel.ListToursViewModel;
import at.fhtw.viewmodel.SearchViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;


public class SearchView {

    public static final int PAGE_ITEMS_COUNT = 10;

    private SearchViewModel searchViewModel = new SearchViewModel();

    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Label searchLabel;

    /*@FXML
    private void initialize() {

        //searchField.textProperty().bindBidirectional(searchViewModel.searchStringProperty());

        // search panel
        searchButton.setText("Search");
        searchButton.setOnAction(event -> loadData());
        searchButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        System.out.println("search1");
        searchField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                searchButton.setText("Wuhuu!!");
            }
        });
        System.out.println("search2");
        /*searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchLabel.setText(newValue);
        });*/
    //}

    /*private void loadData() {
        searchViewModel.search();
    }*/

    @FXML
    private void initialize() {

            searchField.textProperty().bindBidirectional(searchViewModel.searchStringProperty());

            // search panel
            searchButton.setText("Search");
            searchButton.setOnAction(event -> loadData());
            searchButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            searchField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                loadData();
            }
            });
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchLabel.setText(newValue);
            });
        }

    private void loadData() {
        ListToursViewModel toursViewModel = ListToursView.getInstance();

        if(searchField.getText() == null || searchField.getText().isBlank() || searchField.getText().isEmpty())
        {
            toursViewModel.clearItems();
            toursViewModel.initList();
            return;
        }

        toursViewModel.clearItems();
        toursViewModel.filterList(searchField.getText());
    }

}
