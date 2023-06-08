package at.fhtw.view;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageView extends Dialog<Void> {
    String message;
    String title;
    public MessageView(String message, String title) {
        super();
        this.message = message;
        this.title = title;

        initialize();
    }


    public void initialize() {
        setTitle(title);

        ButtonType cancle = new ButtonType("Cancle", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(cancle);
        ButtonType agree = new ButtonType("Agree", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(agree);

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
