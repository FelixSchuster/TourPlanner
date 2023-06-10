package at.fhtw.view.popUps;

import at.fhtw.exceptions.*;
import at.fhtw.models.TourLog;
import at.fhtw.view.ListToursView;
import at.fhtw.view.ShowTourLogsView;
import at.fhtw.viewmodel.ShowTourLogsViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateTourLogPopUpView extends Dialog<Void> {
    private static final Logger logger = LogManager.getLogger(CreateTourLogPopUpView.class);
    Integer tourId;
    String title;
    @FXML
    private Stage popupWindow = new Stage();
    @FXML
    private Text feedbackText = new Text();
    @FXML
    public TextField totalTimeTextField = new TextField();
    @FXML
    private TextField difficultyTextField = new TextField();
    @FXML
    private TextField ratingTextField = new TextField();
    @FXML
    private TextArea commentTextField = new TextArea();
    public CreateTourLogPopUpView(Integer tourId, String title) {
        super();
        this.tourId = tourId;
        this.title = title;

        initialize();
    }

    public void initialize() {

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle(title);

        feedbackText.setStyle("-fx-text-fill: red;");

        VBox root = new VBox();
        root.setPadding(new Insets(8.0));


        Label titleLabel = new Label("Update Tour Log");
        titleLabel.setFont(new Font(14.0));

        Separator separator1 = new Separator();
        separator1.setPrefWidth(200.0);

        Text totalTimeText = new Text("Total Time");

        Text difficultyText = new Text("Difficulty");

        Text ratingText = new Text("Rating");

        Text commentText = new Text("Comment");

        HBox submitButtonContainer = new HBox();
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> onSubmit());
        submitButtonContainer.getChildren().add(submitButton);
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                popupWindow.close();
            }
        });
        submitButtonContainer.getChildren().add(cancelButton);
        submitButtonContainer.setAlignment(Pos.BASELINE_RIGHT);
        submitButtonContainer.setSpacing(10.0);

        Separator separator2 = new Separator();
        separator2.setPrefWidth(200.0);

        root.setSpacing(10.0);
        root.getChildren().addAll(
                titleLabel, separator1,
                totalTimeText, totalTimeTextField,
                difficultyText, difficultyTextField,
                ratingText, ratingTextField,
                commentText, commentTextField,
                submitButtonContainer,
                separator2,
                feedbackText
        );

        Scene scene = new Scene(root, 300, 370);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }

    public void onSubmit()
    {
        try {

            if (totalTimeTextField.getText() == null ||
                    totalTimeTextField.getText().isBlank() ||
                    totalTimeTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a Total Time!");
                return;
            } else if (difficultyTextField.getText() == null ||
                    difficultyTextField.getText().isBlank() ||
                    difficultyTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a Difficulty!");
                return;
            } else if (ratingTextField.getText() == null ||
                    ratingTextField.getText().isBlank() ||
                    ratingTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a rating!");
                return;
            }

            TourLog createdTourLog = new TourLog(commentTextField.getText(), Integer.parseInt(difficultyTextField.getText()), Integer.parseInt(totalTimeTextField.getText()), Integer.parseInt(ratingTextField.getText()));
            System.out.println("tour: " + createdTourLog);
            ShowTourLogsView.getInstance().createTourLog(tourId, createdTourLog);
            new DialogView("Tour Log successfully created!", "Create Tour Log");

            ShowTourLogsView.getInstance().showTourLogs(tourId);

            popupWindow.close();
        }catch (NoContentException e) {
            logger.info("CreateTourLogPopUpView.getTourLogs() - " + e.getMessage());
        } catch (NotFoundException e) {
            logger.info("CreateTourLogPopUpView.getTour() - " + e.getMessage());
            new DialogView("Tour could not be found", "Delete Tour Log");
        } catch (InternalServerErrorException e) {
            logger.error("CreateTourLogPopUpView.getTour() - " + e.getMessage());
            new DialogView("Internal Server Issues\nThe Tour Tour Log could not be deleted!", "Delete Tour Log");
        } catch (FailedToParseImageFileException e) {
            logger.error("CreateTourLogPopUpView.getTour() - " + e.getMessage());
            new DialogView("Failed to parse image\nThe Tour Tour Log could be not shown!", "Delete Tour Log");
        } catch (FailedToSendRequestException e) {
            logger.error("CreateTourLogPopUpView.getTour() - " + e.getMessage());
            new DialogView("Failed to send Request\nThe Tour Tour Log could not be deleted!", "Delete Tour Log");
        } catch (NumberFormatException e) {
            feedbackText.setText("Please enter a number!");
        }
    }
}
