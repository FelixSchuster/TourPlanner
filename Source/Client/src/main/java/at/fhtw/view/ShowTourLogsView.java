package at.fhtw.view;

import at.fhtw.models.TourLog;
import at.fhtw.services.TourLogService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowTourLogsView extends ApplicationView{
    public VBox headers;
    public VBox tourLogs;
    public AnchorPane TourLogPane;
    @FXML
    private VBox dataContainer;
    private GridPane gridpane = new GridPane();

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        Label tourLogHeader = new Label("name");
        dataContainer.getChildren().add(tourLogHeader);
        TourLogService tourLogService = new TourLogService();
        javafx.scene.control.ScrollPane scrollPane = new ScrollPane(dataContainer);
        scrollPane.setFitToWidth(true);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        TourLogPane.getChildren().add(scrollPane);

// Set the preferred size of the ScrollPane
        scrollPane.setPrefSize(400, 300);


        //List<TourLog> tourLogs= tourLogService.getTourLogs(3);
        //logger.info("BusinessLogic.getTourLogs() - tourLogs retrieved successfully: " + tourLogs);
        tourLogService.getTourLogs(3).forEach(p -> {
            showTourLogs(p);
        });

        //VBox vbox()
    }

    public void showTourLogs(TourLog tourLog)
    {
        HBox hbox = new HBox();
        VBox headers = new VBox();
        VBox content = new VBox();
        VBox buttons = new VBox();

        HBox.setHgrow(buttons, javafx.scene.layout.Priority.ALWAYS);

        hbox.setSpacing(50);
        headers.setSpacing(5);
        content.setSpacing(5);
        buttons.setSpacing(0);
        dataContainer.setSpacing(10.0);

        AnchorPane.setRightAnchor(dataContainer, 10.0);
        AnchorPane.setLeftAnchor(dataContainer, 10.0);
        AnchorPane.setTopAnchor(dataContainer, 10.0);
        AnchorPane.setBottomAnchor(dataContainer, 10.0);

        AnchorPane.setRightAnchor(dataContainer, 40.0);
        dataContainer.getChildren().add(hbox);
        hbox.getChildren().addAll(headers, content, buttons);

        Label date = new Label("Date: ");
        date.getStyleClass().add("bold-headers");
        Label totalTime = new Label("Total Time: ");
        totalTime.getStyleClass().add("bold-headers");
        Label difficulty = new Label("Difficulty: ");
        difficulty.getStyleClass().add("bold-headers");
        Label rating = new Label("Rating: ");
        rating.getStyleClass().add("bold-headers");
        Label comment = new Label("Comment: ");
        comment.getStyleClass().add("bold-headers");

        headers.getChildren().addAll(date, totalTime, difficulty, rating, comment);

        Label dateTourLog = new Label(tourLog.getDate());
        Label totalTimeTourLog = new Label(Integer.toString(tourLog.getTotalTime()));
        Label difficultyTourLog = new Label(Integer.toString(tourLog.getDifficulty()));
        Label ratingTourLog = new Label(Integer.toString(tourLog.getRating()));
        Label commentTourLog = new Label(tourLog.getComment());

        dataContainer.getChildren().add(new Separator());

        content.getChildren().addAll(dateTourLog, totalTimeTourLog, difficultyTourLog, ratingTourLog, commentTourLog);

        Button updateButton = new Button("Update");
        updateButton.setMinWidth(80);
        Button deleteButton = new Button("Delete");
        deleteButton.setMinWidth(80);
        buttons.getChildren().addAll(updateButton, deleteButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        deleteButton.setOnAction(e ->
                dataContainer.getChildren().clear()

        );






    }


}
