package at.fhtw.view;

import at.fhtw.view.popUps.DialogView;
import at.fhtw.viewmodel.ApplicationViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
// import org.springframework.context.ApplicationEventPublisher;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationView implements Initializable {

    ApplicationViewModel applicationViewModel;

    @FXML
    BorderPane layout;
    @FXML
    HBox HBoxDarkmodeButton;
    @FXML
    Button darkmode;
    @FXML
    Button lightmode;
    private String defaultStylesheet = "file:src/main/resources/at/fhtw/css_sheets/application.css";
    private String darkModeStylesheet = "file:src/main/resources/at/fhtw/css_sheets/darkmode.css";
    private boolean darkModeEnabled;
    SimpleObjectProperty<Stage> stage = new SimpleObjectProperty<>();

    public ApplicationView()
    {
        applicationViewModel = new ApplicationViewModel();
        darkModeEnabled = false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void onTogglDarkmode(ActionEvent actionEvent) {

        lightmode.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                layout.getStylesheets().clear();
                layout.getStylesheets().add(defaultStylesheet);

                darkmode.setText("Dark");
                lightmode.setText("");
                lightmode.setDisable(true);
                darkmode.setDisable(false);
            }
        });

        darkmode.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                layout.getStylesheets().clear(); // Clear the existing stylesheets
                layout.getStylesheets().add(darkModeStylesheet); // Apply the dark mode stylesheet

                lightmode.setText("Light");
                darkmode.setText("");

                darkmode.setDisable(true);
                lightmode.setDisable(false);
            }
        });
    }
}
