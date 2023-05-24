module tourplanner {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires itextpdf;
    requires spring.context;
    requires spring.beans;

    opens at.fhtw.client to javafx.graphics, javafx.fxml;
    exports at.fhtw.client;
    exports at.fhtw.client.view;
    opens at.fhtw.client.view to javafx.fxml, javafx.graphics;
    exports at.fhtw.client.viewmodel;
    opens at.fhtw.client.viewmodel to javafx.fxml, javafx.graphics;
    exports at.fhtw.client.businessLogic;
    opens at.fhtw.client.businessLogic to javafx.graphics, javafx.fxml;
    exports at.fhtw.client.services;
    opens at.fhtw.client.services to javafx.graphics, javafx.fxml;
    exports at.fhtw.client.models;
    opens at.fhtw.client.models to javafx.graphics, javafx.fxml;


}