package at.fhtw.view;

import at.fhtw.models.TourListEntry;
import at.fhtw.viewmodel.ListToursViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle(title);

        Label label = new Label(message);

        Button submitButton= new Button("Submit");
        Button cancelButton= new Button("Cancel");

        submitButton.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                ListToursViewModel listToursViewModel = ListToursView.getInstance();
                listToursViewModel.deleteTour(tourListEntry.getTourId());
                listToursViewModel.clearItems();
                listToursViewModel.initList();
                popupWindow.close();
            }
        });

        cancelButton.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                popupWindow.close();
            }
        });
        HBox buttonBox = new HBox(10, submitButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(16, 16, 16, 16));

        VBox layout = new VBox();
        layout.setPadding(new Insets(16, 16, 16, 16));
        layout.setMaxWidth(300);

        layout.getChildren().addAll(label, buttonBox);

        Scene scene = new Scene(layout);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }
}
