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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


public class AddKontoController implements Controller {

    private UIUtils uiUtils;
    private Nutzer currentNutzer;
    private Konto currentKonto;
    private List<Listener> listenerList = new ArrayList<>();

    @FXML
    Label kontonameLabel, kontostandLabel, beschreibungLabel;

    @FXML
    TextField kontoname, kontostand, beschreibung;

    @FXML
    Button addKontoButton;

    @Override
    public void build() throws IOException {
        kontonameLabel.setPadding(new Insets(5));
        kontostandLabel.setPadding(new Insets(5));
        beschreibungLabel.setPadding(new Insets(5));
        kontoname.setPadding(new Insets(5));
        kontostand.setPadding(new Insets(5));
        beschreibung.setPadding(new Insets(5));
        addKontoButton.setPadding(new Insets(5));

        addKontoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    uiUtils.createKonto(new BigDecimal(kontostand.getText()), currentNutzer, kontoname.getText(), beschreibung.getText(), 0);
                    notifyListeners();

                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Fehler");
                    alert.setContentText("Bei der Erstellung des Kontos ist ein Fehler aufgetreten. Beachten Sie die Formatierung ihrer Eingabe.");
                    alert.showAndWait();
                }
                Stage stage = (Stage) addKontoButton.getScene().getWindow();
                stage.close();
            }
        });


    }

    @Override
    public void addUIUtils(UIUtils uiUtils) {
        this.uiUtils = uiUtils;
    }

    @Override
    public void setCurrentNutzer(Nutzer currentNutzer) {
        this.currentNutzer = currentNutzer;
    }

    @Override
    public void setCurrentKonto(Konto currentKonto) {
        this.currentKonto = currentKonto;
    }

    @Override
    public void update(Observable observable) throws IOException {

    }

    @Override
    public void addListener(Listener listener) {
        this.listenerList.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {

    }

    @Override
    public void notifyListeners() throws IOException {
        for (Listener listener : listenerList) {
            listener.update(this);
        }
    }

    public UIUtils getUiUtils() {
        return uiUtils;
    }

    public void setUiUtils(UIUtils uiUtils) {
        this.uiUtils = uiUtils;
    }

    public Nutzer getCurrentNutzer() {
        return currentNutzer;
    }

    public Konto getCurrentKonto() {
        return currentKonto;
    }
}
