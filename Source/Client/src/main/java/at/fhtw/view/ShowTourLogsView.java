package at.fhtw.view;

import at.fhtw.exceptions.*;
import at.fhtw.models.TourLog;
import at.fhtw.view.popUps.CreateTourLogPopUpView;
import at.fhtw.view.popUps.DeleteTourLogMessageView;
import at.fhtw.view.popUps.DialogView;
import at.fhtw.view.popUps.UpdateTourLogPopUpView;
import at.fhtw.viewmodel.ShowTourLogsViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowTourLogsView extends ApplicationView{
    private static final Logger logger = LogManager.getLogger(ShowTourLogsView.class);
    private static ShowTourLogsViewModel showTourLogsViewModel;
    public VBox headers;
    @FXML
    public VBox tourLogs;
    @FXML
    public AnchorPane tourLogPane;
    @FXML
    public ScrollPane scrollPane;
    public VBox paneContainer;
    @FXML
    private VBox dataContainer;


    public ShowTourLogsView()
    {
        this.showTourLogsViewModel = new ShowTourLogsViewModel();
    }

    public static ShowTourLogsViewModel getInstance()
    {
        if(showTourLogsViewModel == null)
        {
            showTourLogsViewModel = new ShowTourLogsViewModel();
        }
        return showTourLogsViewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {

        createInformationTextField("Please select a Tour to see Information!");

        showTourLogsViewModel.hideTourInformationProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                paneContainer.getChildren().clear();
                createInformationTextField("Please select a Tour to see Information!");
                showTourLogsViewModel.setHideTourInformation(false);
            }
        });

        showTourLogsViewModel.showTourInformationProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                paneContainer.getChildren().clear();
                paneContainer.setAlignment(Pos.BASELINE_LEFT);
                showTourLogs();
                showTourLogsViewModel.setShowTourInformation(false);
            }
        });

        showTourLogsViewModel.isNoTourLogContentProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                paneContainer.getChildren().clear();
                createInformationTextFieldWithButton("This Tour has no Tour Logs");
                showTourLogsViewModel.setIsNoTourLogContent(false);
            }
        });
    }

    private void createInformationTextField(String text)
    {
        paneContainer.setAlignment(Pos.CENTER);
        Label informationTextField = new Label();
        informationTextField.setText(text);
        informationTextField.setAlignment(Pos.CENTER);
        paneContainer.getChildren().add(informationTextField);
    }

    private void createInformationTextFieldWithButton(String text)
    {
        HBox hbox = new HBox();
        AnchorPane.setRightAnchor(hbox, 0.0);
        AnchorPane.setLeftAnchor(hbox, 0.0);
        AnchorPane.setTopAnchor(hbox, 0.0);
        AnchorPane.setBottomAnchor(hbox, 0.0);
        VBox informstionTextBox = new VBox();
        informstionTextBox.setSpacing(10);
        informstionTextBox.setAlignment(Pos.CENTER);

        hbox.setAlignment(Pos.CENTER);
        Label informationTextField = new Label();
        informationTextField.setText(text);
        informationTextField.setAlignment(Pos.CENTER);

        Button createTourLogButton = new Button("Create Tour Log");
        createTourLogButton.setAlignment(Pos.CENTER);

        informstionTextBox.getChildren().add(informationTextField);
        informstionTextBox.getChildren().add(createTourLogButton);
        hbox.getChildren().addAll(informstionTextBox);
        paneContainer.getChildren().add(hbox);

        createTourLogButton.setOnAction(e -> createTourlog(showTourLogsViewModel.getTourListEntry().getTourId()));
    }

    public void showTourLogs()
    {
        dataContainer = new VBox();
        dataContainer.setSpacing(10);
        dataContainer.setPadding(new Insets(15, 15, 15, 15));

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 0, 10, 0));
        hbox.setSpacing(10.0);
        dataContainer.getChildren().add(hbox);

        VBox tourNameBox = new VBox();
        VBox headerLabelBox = new VBox();
        VBox createButtonBox = new VBox();

        HBox.setHgrow(createButtonBox, javafx.scene.layout.Priority.ALWAYS);

        Label tourName = new Label("Tour Name: ");
        tourNameBox.getChildren().add(tourName);
        Label tourLogHeader = new Label(showTourLogsViewModel.getTourListEntry().getName());
        headerLabelBox.getChildren().add(tourLogHeader);

        Button createTourLogButton = new Button("Create Tour Log");
        createTourLogButton.setMinWidth(80);
        createButtonBox.getChildren().add(createTourLogButton);
        createButtonBox.setAlignment(Pos.CENTER_RIGHT);

        hbox.getChildren().addAll(tourNameBox, headerLabelBox, createButtonBox);
        createTourLogButton.setOnAction(e -> createTourlog(showTourLogsViewModel.getTourListEntry().getTourId()));
        dataContainer.getChildren().add(new Separator());

        javafx.scene.control.ScrollPane scrollPane = new ScrollPane(dataContainer);
        scrollPane.setFitToWidth(true);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        paneContainer.getChildren().add(scrollPane);
        AnchorPane.setRightAnchor(dataContainer, 0.0);
        AnchorPane.setLeftAnchor(dataContainer, 0.0);
        AnchorPane.setTopAnchor(dataContainer, 0.0);
        AnchorPane.setBottomAnchor(dataContainer, 0.0);

        scrollPane.setPrefSize(400, 600);

        showTourLogsViewModel.getTourLogs().forEach(p -> {
            createTourLogsView(p);
        });
    }

    public void createTourLogsView(TourLog tourLog)
    {
        HBox hbox = new HBox();
        VBox headers = new VBox();
        VBox content = new VBox();
        VBox buttons = new VBox();

        HBox.setHgrow(buttons, javafx.scene.layout.Priority.ALWAYS);

        headers.setSpacing(5);
        content.setSpacing(5);
        buttons.setSpacing(0);
        dataContainer.setSpacing(10.0);

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

        updateButton.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                updateTourLog(tourLog);
            }
        });

        deleteButton.setOnAction(e -> deleteTourLog(tourLog));
    }

    public void createTourlog(Integer tourId)
    {
        try {
            new CreateTourLogPopUpView(tourId, "Create Tour");
            ShowTourLogsView.getInstance().hideTourLogs();
        } catch (NoContentException e) {
            logger.info("ShowTourLogsView.getTourLogs() - " + e.getMessage());
        } catch (NotFoundException e) {
            logger.info("ShowTourLogsView.getTour() - " + e.getMessage());
            new DialogView("Tour could not be found", "Create Tour");
        } catch (InternalServerErrorException e) {
            logger.error("ShowTourLogsView.getTour() - " + e.getMessage());
            new DialogView("Internal Server Issues\nThe Tour Information could not be created!", "Create Tour");
        } catch (FailedToParseImageFileException e) {
            logger.error("ShowTourLogsView.getTour() - " + e.getMessage());
            new DialogView("Failed to parse image\nnThe Tour Tour Log could be not shown!", "Create Tour");
        }catch (FailedToSendRequestException e) {
            logger.error("ShowTourLogsView.getTour() - " + e.getMessage());
            new DialogView("Failed to send Request\nThe Tour Information could not be created!", "Create Tour");
        }

    }

    public void deleteTourLog(TourLog tourLog)
    {
        try {
            new DeleteTourLogMessageView(tourLog, showTourLogsViewModel.getTourListEntry().getTourId(), "Delete Tour Log");
        } catch (NoContentException e) {
            logger.info("ShowTourLogsView.getTourLogs() - " + e.getMessage());
        } catch (NotFoundException e) {
            logger.info("ShowTourInformationView.getTour() - " + e.getMessage());
            new DialogView("Tour could not be found", "Delete Tour Log");
        } catch (InternalServerErrorException e) {
            logger.error("ShowTourInformationView.getTour() - " + e.getMessage());
            new DialogView("Internal Server Issues\nThe Tour Tour Log could not be deleted!", "Delete Tour Log");
        } catch (FailedToParseImageFileException e) {
            logger.error("ShowTourInformationView.getTour() - " + e.getMessage());
            new DialogView("Failed to parse image\nThe Tour Tour Log could be not shown!", "Delete Tour Log");
        } catch (FailedToSendRequestException e) {
            logger.error("ShowTourInformationView.getTour() - " + e.getMessage());
            new DialogView("Failed to send Request\nThe Tour Tour Log could not be deleted!", "Delete Tour Log");
        }
    }
    public void updateTourLog(TourLog tourLog)
    {
        try {
            new UpdateTourLogPopUpView(tourLog, showTourLogsViewModel.getTourListEntry().getTourId(), "Update Tour");
        } catch (NoContentException e) {
            logger.info("ShowTourLogsView.getTourLogs() - " + e.getMessage());
        } catch (NotFoundException e) {
            logger.info("ShowTourInformationView.getTour() - " + e.getMessage());
            new DialogView("Tour could not be found", "Update Tour");
        } catch (InternalServerErrorException e) {
            logger.error("ShowTourInformationView.getTour() - " + e.getMessage());
            new DialogView("Internal Server Issues\nThe Tour Information could not be shown!", "Update Tour");
        } catch (FailedToParseImageFileException e) {
            logger.error("ShowTourInformationView.getTour() - " + e.getMessage());
            new DialogView("Failed to parse image\nThe Tour Tour Log could not be shown!", "Update Tour");
        } catch (FailedToSendRequestException e) {
            logger.error("ShowTourInformationView.getTour() - " + e.getMessage());
            new DialogView("Failed to send Request\nThe Tour Information could not be shown!", "Update Tour");
        }
    }
}
