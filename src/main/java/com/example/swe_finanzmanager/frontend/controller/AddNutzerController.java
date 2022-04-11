package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddNutzerController implements Controller {

    private UIUtils uiUtils;
    private List<Listener> listenerList = new ArrayList<>();

    @FXML
    Label vornameLabel, nachnameLabel;

    @FXML
    TextField vorname, nachname;

    @FXML
    Button addNutzerButton;

    @Override
    public void build() throws IOException {
        vornameLabel.setPadding(new Insets(5));
        nachnameLabel.setPadding(new Insets(5));
        vorname.setPadding(new Insets(5));
        nachname.setPadding(new Insets(5));
        addNutzerButton.setPadding(new Insets(5));

        addNutzerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    uiUtils.createNutzer(vorname.getText(), nachname.getText(), 0);
                    notifyListeners();

                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Fehler");
                    alert.setContentText("Bei der Erstellung des Nutzers ist ein Fehler aufgetreten. Beachten Sie die Formatierung ihrer Eingabe.");
                    alert.showAndWait();
                }
                Stage stage = (Stage) addNutzerButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    @Override
    public void addUIUtils(UIUtils uiUtils) {
        this.uiUtils = uiUtils;
    }

    @Override
    public void setCurrentNutzer(Nutzer currentNutzer) {}

    @Override
    public void setCurrentKonto(Konto currentKonto) {}

    @Override
    public void update(Observable observable) throws IOException {}

    @Override
    public void addListener(Listener listener) {
        this.listenerList.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {}

    @Override
    public void notifyListeners() throws IOException {
        for (Listener listener : listenerList) {
            listener.update(this);
        }
    }
}
