package at.fhtw.client.view;

import at.fhtw.client.viewmodel.AddTourViewModel;
import at.fhtw.client.viewmodel.ListToursViewModel;
import at.fhtw.client.viewmodel.ReloadToursViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;

public class ReloadTourView implements Initializable {

    //private ReloadToursViewModel reloadToursViewModel = new ReloadToursViewModel();
    @FXML
    private Button reloadButton;

    @FXML
    private Text feedbackText;
    @FXML
    private TextField nameTextField;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
    }

    public ReloadTourView()
    {

    }

    public void reload() {
        ListToursView toursView = ListToursView.getInstance();
        ListToursViewModel toursViewModel = toursView.getListToursViewModel();

        System.out.println("before");
        System.out.println(toursViewModel.getTourListItems());
        System.out.println("clear");
        toursViewModel.clearItems();
        toursViewModel.initList();
        System.out.println("danach");
        System.out.println(toursViewModel.getTourListItems());
        System.out.println("yes");
    }
}
