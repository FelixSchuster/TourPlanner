package at.fhtw.view;

import at.fhtw.viewmodel.ApplicationViewModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
// import org.springframework.context.ApplicationEventPublisher;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationView implements Initializable {

    // ApplicationEventPublisher publisher;

    @FXML
    BorderPane layout;

    @FXML Label tbMonitorStatus;
    Circle monitorStatusIcon = new Circle(8);

    // create custom viewmodel
    private ApplicationViewModel viewModel = new ApplicationViewModel();
    SimpleObjectProperty<Stage> stage = new SimpleObjectProperty<>();

    // add fx:id and use intelliJ to create field in controller

    public ApplicationView()
    {
        System.out.println("Controller created");
        // this.publisher = publisher;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage.addListener((obv, o, n) -> n.setTitle(resourceBundle.getString("app.title")));
        tbMonitorStatus.setGraphic(monitorStatusIcon);
    }

    @FXML
    public void onFileClose(ActionEvent event) {
        // publisher.publishEvent(new ApplicationShutdownEvent(event.getSource()));
    }

    @FXML
    public void onHelpAbout(ActionEvent event) {
        //new AboutDialogView().show();
    }
}
