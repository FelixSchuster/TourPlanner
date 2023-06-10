package at.fhtw.view;

import at.fhtw.view.popUps.DialogView;
import at.fhtw.viewmodel.ApplicationViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
// import org.springframework.context.ApplicationEventPublisher;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationView implements Initializable {

    ApplicationViewModel applicationViewModel;

    @FXML
    BorderPane layout;

    @FXML
    Label tbMonitorStatus;
    Circle monitorStatusIcon = new Circle(8);

    // create custom viewmodel
    private ApplicationViewModel viewModel = new ApplicationViewModel();
    SimpleObjectProperty<Stage> stage = new SimpleObjectProperty<>();

    // add fx:id and use intelliJ to create field in controller

    public ApplicationView()
    {
        applicationViewModel = new ApplicationViewModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage.addListener((obv, o, n) -> n.setTitle(resourceBundle.getString("app.title")));
        //tbMonitorStatus.setGraphic(monitorStatusIcon);
    }

    @FXML
    public void onFileClose(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void onHelpAbout(ActionEvent event) {
        new DialogView("Hello to our wonderful Tour Planner Application!\nHave fun!", "Tour Planner Information");
    }


    public void onExportButton(ActionEvent actionEvent)
    {
        String filename = "tours_export";
        applicationViewModel.exportTours(filename);
    }

    public void onImportButton(ActionEvent actionEvent)
    {

        Stage popupWindow = new Stage();

        String filename = "";
        FileChooser fileChooser = new FileChooser();

        // Set the title and initial directory (optional)
        fileChooser.setTitle("Select a File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.csv");

        // Show the file dialog and wait for user selection
        File selectedFile = fileChooser.showOpenDialog(popupWindow);
        fileChooser.getExtensionFilters().addAll(textFilter);

        // Process the selected file
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile);
            System.out.println("Selected file name: " + selectedFile.getName());
            applicationViewModel.importTours(selectedFile.getName());
        } else {
            System.out.println("No file selected.");
        }


    }

    public void onSummarizeButtonButton(ActionEvent actionEvent)
    {
        String filename = "summarize_report";
        applicationViewModel.createSummarizeReport(filename);
    }
}
