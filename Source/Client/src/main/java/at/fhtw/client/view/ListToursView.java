package at.fhtw.client.view;

import at.fhtw.client.businessLogic.BusinessLogic;
import at.fhtw.client.models.Person;
import at.fhtw.client.models.Tour;
import at.fhtw.client.models.TourListEntry;
import at.fhtw.client.services.TourService;
import at.fhtw.client.viewmodel.AddTourViewModel;
import at.fhtw.client.viewmodel.ListToursViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ListToursView implements Initializable {
    public ListToursViewModel listToursViewModel = new ListToursViewModel();
    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    private VBox dataContainer;


    /*public List<Tour> getItems() {
        Tour[] tourtems =
                {
                        new Tour("Item1"),
                        new Tour("Item2"),
                        new Tour("Another"),
                        new Tour("SWE1"),
                        new Tour("FHTW"),
                };
        return new ArrayList<Tour>(Arrays.asList(tourtems));
    */
   /* @FXML
    private void initialize(URL location, ResourceBundle rb) {

        ComboBox<Tour> tourItems = new ComboBox<Tour>(FXCollections.observableList(getItems()));
        List<Tour> tourList = getItems();
       for (Tour value : tourList)
        {
            System.out.println("value: " + value);
        }
        //searchField.textProperty().bindBidirectional(searchViewModel.searchStringProperty());
        tableView.setItems((ObservableList) tourList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory("name"));

        tableView.getColumns().addAll(name);

        dataContainer.getChildren().add(tableView);
        /*personService.getPersonList().forEach(p -> {
            addItem(p);
        });*/
        //personListViewModel.initList();

        // search panel

        /*searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchLabel.setText(newValue);
        });*/
    //}

    @Override
    public void initialize(URL location, ResourceBundle rb){
        System.out.println("drinnen");
        TourService tourService = new TourService();
        System.out.println("1");
        System.out.println("2");
        listToursViewModel.initList();
        ObservableList<Person> masterData = listToursViewModel.getTourListItems();
        System.out.println("davor");
        for (Person value : masterData)
        {
            System.out.println("value: " + value);
        }

        System.out.println("danach");

        tableView.setItems(listToursViewModel.getTourListItems());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        tableView.getColumns().addAll(name);

        dataContainer.getChildren().add(tableView);
        listToursViewModel.initList();
    }
}
