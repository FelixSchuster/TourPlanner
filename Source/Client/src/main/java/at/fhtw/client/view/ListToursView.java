package at.fhtw.client.view;

import at.fhtw.client.models.TourListEntry;
import at.fhtw.client.viewmodel.ListToursViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ListToursView implements Initializable {
    private static ListToursViewModel listToursViewModel;
    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    private VBox dataContainer;
    @FXML
    private Button reloadButton;

    public ListToursView()
    {
        listToursViewModel = new ListToursViewModel();
    }

    public static ListToursViewModel getInstance()
    {
        if(listToursViewModel == null)
        {
            listToursViewModel = new ListToursViewModel();
        }
        return listToursViewModel;
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


        //tableView.setOnAction(event -> loadData());
        //tableView.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        tableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if(tableView.getSelectionModel().getSelectedItem() != null) {
                    TourInformationView.getInstance().changeTourImagePath((TourListEntry) tableView.getSelectionModel().getSelectedItem());
                }
            }
        });
        //searchField.textProperty().addListener((observable, oldValue, newValue) -> {
          //  searchLabel.setText(newValue);
        //});
    }

    public void reload() {
        listToursViewModel.clearItems();
        listToursViewModel.clearItems();
        listToursViewModel.initList();
    }

    public void showInformations(TourListEntry tourListEntry)
    {

        System.out.println("item: ");
        System.out.println(tourListEntry);
    }



}
