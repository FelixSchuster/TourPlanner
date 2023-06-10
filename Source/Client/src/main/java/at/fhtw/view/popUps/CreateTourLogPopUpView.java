package at.fhtw.view.popUps;

import at.fhtw.models.TourLog;
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

public class CreateTourLogPopUpView extends Dialog<Void> {
    private static final Logger logger = LogManager.getLogger(CreateTourLogPopUpView.class);
    Integer tourId;
    String title;
    @FXML
    private Stage popupWindow = new Stage();
    @FXML
    private Label feedbackText = new Label();
    @FXML
    private TextField daysTextField = new TextField();
    @FXML
    private TextField hoursTextField = new TextField();
    @FXML
    private TextField minutesTextField = new TextField();
    @FXML
    private TextField difficultyTextField = new TextField();
    @FXML
    private TextField ratingTextField = new TextField();
    @FXML
    private TextArea commentTextField = new TextArea();
    private String defaultStylesheet = "file:src/main/resources/at/fhtw/css_sheets/application.css";
    public CreateTourLogPopUpView(Integer tourId, String title) {
        super();
        this.tourId = tourId;
        this.title = title;

        initialize();
    }

    public void initialize() {
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle(title);
        feedbackText.getStyleClass().add("feedbackText");

        VBox root = new VBox();
        root.setPadding(new Insets(8.0));
        root.getStylesheets().add(defaultStylesheet);



        Label titleLabel = new Label("Update Tour Log");
        titleLabel.setFont(new Font(14.0));

        Separator separator1 = new Separator();
        separator1.setPrefWidth(200.0);

        HBox totalTimeContainer = new HBox();
        totalTimeContainer.setSpacing(5);
        daysTextField.setMaxWidth(60);
        hoursTextField.setMaxWidth(60);
        minutesTextField.setMaxWidth(60);
        daysTextField.setPromptText("days");
        hoursTextField.setPromptText("hours");
        minutesTextField.setPromptText("minutes");

        Text totalTimeText = new Text("Total Time");
        totalTimeContainer.getChildren().addAll(daysTextField, hoursTextField, minutesTextField);

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
                totalTimeText, totalTimeContainer,
                difficultyText, difficultyTextField,
                ratingText, ratingTextField,
                commentText, commentTextField,
                submitButtonContainer,
                separator2,
                feedbackText
        );

        Scene scene = new Scene(root, 300, 400);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }

    public void onSubmit()
    {
        try {
            System.out.println(daysTextField.getText() + hoursTextField.getText() + minutesTextField.getText() + difficultyTextField.getText() + ratingTextField.getText());

            if (daysTextField.getText() == null ||
                    daysTextField.getText().isBlank() ||
                    daysTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a Total Time!");
                return;
            } else if (hoursTextField.getText() == null ||
                    hoursTextField.getText().isBlank() ||
                    hoursTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a Total Time!");
                return;
            } else if (minutesTextField.getText() == null ||
                    minutesTextField.getText().isBlank() ||
                    minutesTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a Total Time!");
                return;
            }else if (difficultyTextField.getText() == null ||
                    difficultyTextField.getText().isBlank() ||
                    difficultyTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a Difficulty!");
                return;
            } else if (ratingTextField.getText() == null ||
                    ratingTextField.getText().isBlank() ||
                    ratingTextField.getText().isEmpty()) {
                feedbackText.setText("Please enter a rating!");
                return;
            } else if(Integer.parseInt(difficultyTextField.getText()) < 0 || Integer.parseInt(difficultyTextField.getText()) > 5)
            {
                feedbackText.setText("Please enter a difficulty between 0 and 5");
                return;
            } else if(Integer.parseInt(ratingTextField.getText()) < 0 || Integer.parseInt(ratingTextField.getText()) > 5)
            {
                feedbackText.setText("Please enter a rating between 0 and 5");
                return;
            }

            TourLog createdTourLog = new TourLog(commentTextField.getText(),
                    Integer.parseInt(difficultyTextField.getText()),
                    ShowTourLogsView.getInstance().calculateTotalTimeFromInput(Integer.parseInt(daysTextField.getText()),
                    Integer.parseInt(hoursTextField.getText()),
                    Integer.parseInt(minutesTextField.getText())),
                    Integer.parseInt(ratingTextField.getText()));

            ShowTourLogsView.getInstance().createTourLog(tourId, createdTourLog);

            ShowTourLogsView.getInstance().showTourLogs(tourId);

            popupWindow.close();
        } catch (NumberFormatException e) {
            feedbackText.setText("Please enter a number!");
        }
    }
}
