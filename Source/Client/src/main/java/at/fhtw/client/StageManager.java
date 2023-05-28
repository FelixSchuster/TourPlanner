package at.fhtw.client;

import at.fhtw.client.events.ApplicationErrorEvent;
import at.fhtw.client.events.ApplicationShutdownEvent;
import at.fhtw.client.events.ApplicationStartupEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.controlsfx.control.Notifications;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class StageManager {
    public static final String PRIMARY_STAGE_PROPERTY = "primaryStage";
    
    private final Application application;
    private final ViewManager viewManager;

    private final Property<Stage> primaryStage;
    
    public StageManager(Application application, ViewManager viewManager) {
        this.application = application;
        this.viewManager = viewManager;

        this.primaryStage = new SimpleObjectProperty<Stage>(application, PRIMARY_STAGE_PROPERTY);
    }

    public Optional<Stage> getPrimaryStage() {
        return Optional.ofNullable(primaryStage.getValue());
    }

    public Property<Stage> primaryStageProperty() {
        return primaryStage;
    }

    @EventListener
    public void onStartupEvent(ApplicationStartupEvent event) {
        log.debug("Starting application, loading Application scene");

        Stage stage = event.getStage();
        primaryStage.setValue(stage);
    
        try {
            // fxml created with SceneBuilder
            System.out.println("fxml start");
           // Parent root = FXMLLoader.load(TourPlannerClient.class.getResource("mainWindow.fxml"));
            System.out.println("fxml loaded");

            // bootstrap "window" named stage
            //primaryStage.setTitle("Tour Planner");

            // set scene into stage in defined size
            //primaryStage.setScene(new Scene(root, 800, 500));
            //primaryStage.setMinWidth(300);
            //primaryStage.setMinHeight(300);
            //System.out.println("set scene");

            // let's go
            //primaryStage.show();
            System.out.println("show stage");
            Parent parent = FXMLLoader.load(TourPlannerClient.class.getResource("mainWindow.fxml"));
            //Parent parent = (Parent) viewManager.load("mainWindow.fxml", stage);
            stage.setScene(new Scene(parent));
        } catch (IOException e) {
            log.error("Error occurred while creating Application scene", e);
        }                

        stage.show();  
    }

    @EventListener
    public void onShutdownEvent(ApplicationShutdownEvent event) throws Exception {
        application.stop();
    }

    @EventListener 
    public void onApplicationError(ApplicationErrorEvent event) {
        Platform.runLater(() -> {
            Notifications.create()
                .owner(primaryStage.getValue())
                .text(event.getMessage())
                .position(Pos.BOTTOM_CENTER)
                .showError();
        });
    }
}
