package at.fhtw.client.events;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class ApplicationStartupEvent extends ApplicationEvent {
    private Stage stage;

    public ApplicationStartupEvent(Object source, Stage stage) {
        super(source);
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
