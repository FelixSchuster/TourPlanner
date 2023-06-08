package at.fhtw.view;

import at.fhtw.models.TourListEntry;
import at.fhtw.viewmodel.ListToursViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class DeleteTourMessageView extends Dialog<Void> {
    TourListEntry tourListEntry;
    String message;
    String title;
    public DeleteTourMessageView(TourListEntry tourListEntry, String title) {
        super();
        this.tourListEntry = tourListEntry;
        this.message = "Are you sure you want to delete the tour with id " + tourListEntry.getTourId();
        this.title = title;

        initialize();
    }


    public void initialize() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(title);


        //getDialogPane.set
        //ButtonType cancleButton = new ButtonType("Cancle", ButtonBar.ButtonData.OK_DONE);
        //getDialogPane().getButtonTypes().add(cancleButton);

        Button cancelButton = new Button("Cancel");

        Button agreeButton = new Button("Agree");


        cancelButton.setAlignment(Pos.CENTER);
        agreeButton.setAlignment(Pos.CENTER);


       /* VBox layout = new VBox(8);
        getDialogPane().setContent(layout);
        layout.setAlignment(Pos.CENTER);

        //getDialogPane().getChildren().add(cancleButton);
        //getDialogPane().getChildren().add(agreeButton);

        layout.setPadding(new Insets(16, 16, 16, 16));
        layout.setMaxWidth(300);

        Text content = new Text(message);
        content.setWrappingWidth(layout.getMaxWidth());
        content.maxWidth(layout.getMaxWidth());
        layout.getChildren().add(content);*/

        HBox buttonBox = new HBox(10, agreeButton, cancelButton);
        buttonBox.setStyle("-fx-alignment: center-right; -fx-padding: 10px;");
        //layout.getChildren().add(cancelButton);
        //layout.getChildren().add(agreeButton);


        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-font-size: 14px;");

        BorderPane dialogPane = new BorderPane();
        dialogPane.setCenter(messageLabel);
        dialogPane.setBottom(buttonBox);

        dialog.getDialogPane().setContent(dialogPane);

        agreeButton.setOnAction(event -> dialog.setResult(ButtonType.OK));
        cancelButton.setOnAction(event -> dialog.setResult(ButtonType.OK));



        agreeButton.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                ListToursViewModel listToursViewModel = ListToursView.getInstance();
                listToursViewModel.deleteTour(tourListEntry.getTourId());
            }
        });
    }
}
