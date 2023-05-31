package at.fhtw.client.view;

import at.fhtw.client.events.ApplicationShutdownEvent;
import at.fhtw.client.viewmodel.MainViewModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.springframework.context.ApplicationEventPublisher;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    ApplicationEventPublisher publisher;

    @FXML
    BorderPane layout;

    @FXML Label tbMonitorStatus;
    Circle monitorStatusIcon = new Circle(8);

    // create custom viewmodel
    private MainViewModel viewModel = new MainViewModel();
    SimpleObjectProperty<Stage> stage = new SimpleObjectProperty<>();

    // add fx:id and use intelliJ to create field in controller

    public MainView()
    {
        System.out.println("Controller created");
        this.publisher = publisher;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage.addListener((obv, o, n) -> n.setTitle(resourceBundle.getString("app.title")));
        tbMonitorStatus.setGraphic(monitorStatusIcon);
    }

    @FXML
    public void onFileClose(ActionEvent event) {
        publisher.publishEvent(new ApplicationShutdownEvent(event.getSource()));
    }

    @FXML
    public void onHelpAbout(ActionEvent event) {
        new AboutDialogView().show();
    }
}
