package at.fhtw.view;

import at.fhtw.models.TourListEntry;
import at.fhtw.viewmodel.ListToursViewModel;
import javafx.event.ActionEvent;
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
        setTitle(title);
        final Button btOk = (Button) getDialogPane().lookupButton(ButtonType.OK);
        btOk.addEventFilter(ActionEvent.ACTION, event -> {
            System.out.println("yess");
        });



        //Button agreeButton = new Button("Agree");
        ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //getDialogPane().getButtonTypes().add(ok);

        //getDialogPane().getChildren().add(agreeButton);
        //getDialogPane().getButtonTypes().add(agreeButton);

        VBox layout = new VBox(8);
        getDialogPane().setContent(layout);

        layout.setPadding(new Insets(16, 16, 16, 16));
        layout.setMaxWidth(300);

        Text content = new Text(message);
        content.setWrappingWidth(layout.getMaxWidth());
        content.maxWidth(layout.getMaxWidth());
        layout.getChildren().add(content);
        //layout.getChildren().add(btOk);

        //agreeButton.setOnAction(event -> onAgreeButton());


        /*agreeButton.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
               DialogPane dialogPane = getDialogPane();

            }
        });*/
    }

    private void onAgreeButton()
    {
        //getDialogPane().getWi
    }
}
