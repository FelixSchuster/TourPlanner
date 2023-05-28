package at.fhtw.client.viewmodel;

import at.fhtw.client.TourPlannerClient;
import at.fhtw.client.events.ApplicationStartupEvent;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TourPlannerApplication extends Application {
    private Logger logger = LoggerFactory.getLogger(TourPlannerApplication.class);
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage stage) throws Exception {
        logger.debug("Starting TutorialApplication");

        applicationContext.publishEvent(new ApplicationStartupEvent(this, stage));
    }

    @Override
    public void init() {
        logger.debug("Initializing Spring ApplicationContext");

        applicationContext = new SpringApplicationBuilder(TourPlannerClient.class)
                .sources(TourPlannerClient.class)
                .initializers(initializers())
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void stop() throws Exception {
        logger.debug("Stopping TutorialApplication");

        applicationContext.close();
        Platform.exit();
    }

    ApplicationContextInitializer<GenericApplicationContext> initializers() {
        return ac -> {
            ac.registerBean(Application.Parameters.class, this::getParameters);
            ac.registerBean(HostServices.class, this::getHostServices);
        };
    }
}
