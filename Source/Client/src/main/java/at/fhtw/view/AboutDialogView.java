package at.fhtw.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AboutDialogView extends Dialog<Void> {
    String message;
    String title;
    public AboutDialogView(String message, String title) {
        super();
        this.message = message;
        this.title = title;

        initialize();
    }


    public void initialize() {
        setTitle(title);

        ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(ok);

        VBox layout = new VBox(8);
        getDialogPane().setContent(layout);

        layout.setPadding(new Insets(16, 16, 16, 16));
        layout.setMaxWidth(300);

        Text content = new Text(message);
        content.setWrappingWidth(layout.getMaxWidth());
        content.maxWidth(layout.getMaxWidth());
        layout.getChildren().add(content);
        //layout.getChildren().add(new Separator(Orientation.HORIZONTAL));
        //layout.getChildren().add(new Hyperlink(resources.getString("about.links.project")));
    }
}
