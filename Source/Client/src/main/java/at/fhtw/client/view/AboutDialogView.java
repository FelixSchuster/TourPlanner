package at.fhtw.client.view;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutDialogView extends Dialog<Void> implements Initializable {
    public AboutDialogView() {
        super();

        initialize(null, ResourceBundle.getBundle("i18n"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTitle("Add new Tour");

        ButtonType ok = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(ok);

        VBox layout = new VBox(8);
        getDialogPane().setContent(layout);

        layout.setPadding(new Insets(16, 16, 16, 16));
        layout.setMaxWidth(300);

        Text content = new Text(resources.getString("about.text"));
        content.setWrappingWidth(layout.getMaxWidth());
        content.maxWidth(layout.getMaxWidth());
        layout.getChildren().add(content);
        layout.getChildren().add(new Separator(Orientation.HORIZONTAL));
        layout.getChildren().add(new Hyperlink(resources.getString("about.links.project")));
    }
}
