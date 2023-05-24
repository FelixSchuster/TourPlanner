package at.fhtw.client;

import at.fhtw.client.businessLogic.BusinessLogic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TourPlannerClient extends Application{
    private static final BusinessLogic businessLogic = new BusinessLogic();

    @Override
    public void start(Stage primaryStage) throws Exception{
        // fxml created with SceneBuilder
        System.out.println("fxml start");
        Parent root = FXMLLoader.load(TourPlannerClient.class.getResource("mainWindow.fxml"));
        System.out.println("fxml loaded");

        // bootstrap "window" named stage
        primaryStage.setTitle("Tour Planner");
        System.out.println("set title");

        // set scene into stage in defined size
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(300);
        System.out.println("set scene");

        // let's go
        primaryStage.show();
        System.out.println("show stage");
    }

    public static void main(String[] args) {
        //businessLogic.createSummarizeReport("C:\\Users\\Felix\\Desktop\\report.pdf");

        //client gui
        //Application.launch(TourPlannerClient.class, args);
        launch(args);

        /*
        Tour createTour = new Tour("Vienna-Bratislava tour", "Vienna-Bratislava (car)", "Vienna", "Bratislava", "car");
        businessLogic.createTour(createTour);

        businessLogic.searchTour("aaaaaaaaaa");
        */

        /*
        businessLogic.getTourList();

        businessLogic.getTour(1);

        Tour updateTour = new Tour(); updateTour.setTourDescription("New description"); updateTour.setDestination("Wien Mitte");
        businessLogic.updateTour(1, updateTour);



        businessLogic.deleteTour(1);

        businessLogic.getTour(1);

        businessLogic.createTour(createTour);

        businessLogic.getTourLogs(2);
        businessLogic.getTourLogs(3);

        TourLog createTourLog = new TourLog("My comment", 3, 8500, 2);
        businessLogic.createTourLog(2, createTourLog);
        businessLogic.createTourLog(2, createTourLog);

        businessLogic.getTourLogs(2);

        businessLogic.getTourLog(1);
        businessLogic.getTourLog(2);
        businessLogic.getTourLog(10);

        TourLog updateTourLog = new TourLog(); updateTourLog.setComment("New comment");
        businessLogic.updateTourLog(1, updateTourLog);

        businessLogic.getTourLogs(2);

        businessLogic.deleteTourLog(1);

        businessLogic.getTourLog(1);

        businessLogic.getTourLogs(2);


        businessLogic.exportTours("C:\\Users\\Felix\\Desktop\\export.json");

        businessLogic.importTours("C:\\Users\\Felix\\Desktop\\import.json");
        */
    }
}
