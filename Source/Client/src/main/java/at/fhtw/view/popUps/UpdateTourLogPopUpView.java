package at.fhtw.view.popUps;

import at.fhtw.exceptions.*;
import at.fhtw.models.TourLog;
import at.fhtw.view.ListToursView;
import at.fhtw.view.ShowTourLogsView;
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

public class UpdateTourLogPopUpView extends Dialog<Void> {
    private static final Logger logger = LogManager.getLogger(UpdateTourLogPopUpView.class);
    TourLog tourLog;
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
    public UpdateTourLogPopUpView(TourLog tourLog, Integer tourId, String title) {
        super();
        this.tourLog = tourLog;
        this.tourId = tourId;
        this.title = title;

        initialize();
    }

    public void initialize() {

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle(title);

        VBox root = new VBox();
        root.setPadding(new Insets(8.0));


        Label titleLabel = new Label("Update Tour Log");
        titleLabel.setFont(new Font(14.0));

        Separator separator1 = new Separator();
        separator1.setPrefWidth(200.0);

        Text totalTimeText = new Text("Total Time");
        totalTimeTextField.setText(Integer.toString(tourLog.getTotalTime()));

        Text difficultyText = new Text("Difficulty");
        difficultyTextField.setText(Integer.toString(tourLog.getDifficulty()));

        Text ratingText = new Text("Rating");
        ratingTextField.setText(Integer.toString(tourLog.getRating()));

        Text commentText = new Text("Comment");
        commentTextField.setText(tourLog.getComment());

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

            TourLog updatedTourLog = new TourLog(commentTextField.getText(), Integer.parseInt(difficultyTextField.getText()), Integer.parseInt(totalTimeTextField.getText()), Integer.parseInt(ratingTextField.getText()));
            ShowTourLogsView.getInstance().updateTourLogs(updatedTourLog, tourLog.getTourLogId());
            new DialogView("Tour Log successfully updated!", "Update Tour Log");

            ShowTourLogsView.getInstance().showTourLogs(tourId);

            popupWindow.close();
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
        } catch (NumberFormatException e) {
            feedbackText.setText("Please enter a number!");
        }
    }
}
