package at.fhtw.client.view;

import at.fhtw.client.models.TourListEntry;
import at.fhtw.client.services.TourService;
import at.fhtw.client.viewmodel.ListToursViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ListToursView implements Initializable {
    private static ListToursView instance;
    private final ListToursViewModel listToursViewModel;
    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    private VBox dataContainer;
    @FXML
    private Button reloadButton;

    public ListToursView()
    {
        listToursViewModel = new ListToursViewModel();
        if(instance == null)
        {
            instance = this;
        }
    }

    public static ListToursView getInstance()
    {
        if(instance == null)
        {
            instance = new ListToursView();
        }
        return instance;
    }

    public ListToursViewModel getListToursViewModel() {
        return listToursViewModel;
    }

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

    public void reload() {
        listToursViewModel.clearItems();
        listToursViewModel.clearItems();
        listToursViewModel.initList();

    }




}
