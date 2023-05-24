package at.fhtw.client.view;

import at.fhtw.client.models.TourListEntry;
import at.fhtw.client.services.TourService;
import at.fhtw.client.viewmodel.ListToursViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ListToursView implements Initializable {

    private ListToursViewModel listToursViewModel = new ListToursViewModel();
    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    private VBox dataContainer;

    @Override
    public void initialize(URL location, ResourceBundle rb){

        tableView.setItems(listToursViewModel.getTourListItems());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory("tourId"));
        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        tableView.getColumns().addAll(id, name);

        dataContainer.getChildren().add(tableView);
        listToursViewModel.initList();
    }
}
