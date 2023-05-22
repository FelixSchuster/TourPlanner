package at.fhtw.client.view;


import at.fhtw.client.viewmodel.SearchViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;


public class SearchView {

    public static final int PAGE_ITEMS_COUNT = 10;

    public SearchViewModel searchViewModel = new SearchViewModel();

    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Label searchLabel;

    @FXML
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
    }

    private void loadData() {
        searchViewModel.search();
    }

}
