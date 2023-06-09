package at.fhtw.view;

import at.fhtw.exceptions.*;
import at.fhtw.models.TourListEntry;
import at.fhtw.view.popUps.DeleteTourMessageView;
import at.fhtw.view.popUps.DialogView;
import at.fhtw.viewmodel.ListToursViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class ListToursView implements Initializable {
    private static final Logger logger = LogManager.getLogger(ListToursView.class);
    private static ListToursViewModel listToursViewModel;
    @FXML
    public TableView tableView = new TableView<>();
    @FXML
    private VBox dataContainer;

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

    @Override
    public void initialize(URL location, ResourceBundle rb){
        try {
            tableView.setItems(listToursViewModel.getTourListItems());
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn id = new TableColumn("ID");
            id.setCellValueFactory(new PropertyValueFactory("tourId"));
            TableColumn name = new TableColumn("NAME");
            name.setCellValueFactory(new PropertyValueFactory("name"));
            tableView.getColumns().addAll(id, name);

            ScrollPane scrollPane = new ScrollPane(tableView);
            scrollPane.setFitToWidth(true);
            dataContainer.getChildren().add(scrollPane);
            listToursViewModel.initList();

            tableView.setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (tableView.getSelectionModel().getSelectedItem() != null) {
                        loadTourInformation();
                        loadTourLogs();
                    }
                }
            });
        } catch (NoContentException e) {
            logger.info("ListToursView.getTourList() - " + e.getMessage());
            new DialogView("No content found!", "Get Tourlist");
        } catch (InternalServerErrorException e) {
            logger.error("ListToursView.getTourList() - " + e.getMessage());
            new DialogView("Internal Server Issues\nTour could not be loaded!", "Get Tourlist");
        } catch (FailedToSendRequestException e) {
            logger.error("ListToursView.getTourList() - " + e.getMessage());
            new DialogView("Failed to send Request", "Get Tourlist");
        }
    }

    public void loadTourInformation()
    {
        try
        {
            ShowTourInformationView.getInstance().changeTourInformation((TourListEntry) tableView.getSelectionModel().getSelectedItem());
        } catch (NotFoundException e) {
            logger.info("ListToursView.getTour() - " + e.getMessage());
            new DialogView("Tour could not be found", "Tour Information");
        } catch (InternalServerErrorException e) {
            logger.error("ListToursView.getTour() - " + e.getMessage());
            new DialogView("Internal Server Issues\nThe Tour Information could not be shown!", "Tour Information");
        } catch (FailedToParseImageFileException e) {
            logger.error("ListToursView.getTour() - " + e.getMessage());
            new DialogView("Failed to parse image\nThe Tour Information could not be shown!", "Tour Information");
        } catch (FailedToSendRequestException e) {
            logger.error("ListToursView.getTour() - " + e.getMessage());
            new DialogView("Failed to send Request\nThe Tour Information could not be shown!", "Tour Information");
        }
    }

    public void loadTourLogs()
    {
        try
        {   ShowTourLogsView.getInstance().setTourListEntry((TourListEntry) tableView.getSelectionModel().getSelectedItem());
            ShowTourLogsView.getInstance().showTourLogs(((TourListEntry) tableView.getSelectionModel().getSelectedItem()).getTourId());
        } catch (NoContentException e) {
            logger.info("ListToursView.getTourLogs() - " + e.getMessage());
        } catch (NotFoundException e) {
            logger.info("ListToursView.getTourLogs() - " + e.getMessage());
            new DialogView("Tour Log could not be found", "Tour Log");
        } catch (InternalServerErrorException e) {
            logger.error("ListToursView.getTourLogs() - " + e.getMessage());
            new DialogView("Internal Server Issues\nThe Tour Log could not be shown!", "Tour Log");
        } catch (FailedToSendRequestException e) {
            logger.error("ListToursView.getTourLogs() - " + e.getMessage());
            new DialogView("Failed to send Request\nThe Tour Log could not be shown!", "Tour Log");
        }
    }

    public void reload(ActionEvent actionEvent) {
        listToursViewModel.clearItems();
        listToursViewModel.initList();
        ShowTourInformationView.getInstance().hideInformation();
        ShowTourLogsView.getInstance().hideTourLogs();
    }

    public void deleteTour(ActionEvent actionEvent)
    {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            try {
                TourListEntry tourListEntry = (TourListEntry) tableView.getSelectionModel().getSelectedItem();
                new DeleteTourMessageView(tourListEntry, "Delete Tour");
            } catch (NotFoundException e) {
                logger.info("ListToursView.deleteTour() - " + e.getMessage());
                new DialogView("Tour Not Found\nTour could not be deleted!", "Delete Tour");
            } catch (InternalServerErrorException e) {
                logger.error("ListToursView.deleteTour() - " + e.getMessage());
                new DialogView("Internal Server Issues\nTour could not be deleted!", "Delete Tour");
            } catch (FailedToSendRequestException e) {
                logger.error("ListToursView.deleteTour() - " + e.getMessage());
                new DialogView("Failed to send Request\nTour could not be deleted!", "Delete Tour");
            }
        }
        else
        {
            new DialogView("Please select a tour!", "Delete Tour");
        }
    }
}
