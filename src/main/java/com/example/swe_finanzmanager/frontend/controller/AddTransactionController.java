package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.sql.Date;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddTransactionController implements Controller {

    private UIUtils uiUtils;
    private Nutzer currentNutzer;
    private Konto currentKonto;
    private List<Listener> listenerList = new ArrayList<>();

    @FXML
    Label titelLabel, betragLabel, beschreibungLabel;

    @FXML
    TextField titel, betrag, beschreibung;

    @FXML
    Button addNewTransaktionButton;

    @Override
    public void build() throws IOException {

        titelLabel.setPadding(new Insets(5));
        betragLabel.setPadding(new Insets(5));
        beschreibungLabel.setPadding(new Insets(5));
        titel.setPadding(new Insets(5));
        betrag.setPadding(new Insets(5));
        beschreibung.setPadding(new Insets(5));


        addNewTransaktionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    uiUtils.createTransaktion(new BigDecimal(betrag.getText()), new Date(System.currentTimeMillis()), currentNutzer, currentKonto, beschreibung.getText(), titel.getText());
                    notifyListeners();
                    System.out.println("Listeners notified!");
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Fehler");
                    alert.setContentText("Bei der Erstellung der Transaktion ist ein Fehler aufgetreten. Beachten Sie die Formatierung ihrer Eingabe.");
                    alert.showAndWait();
                }
                Stage stage = (Stage) addNewTransaktionButton.getScene().getWindow();
                stage.close();
                uiUtils.save();
            }
        });
    }

    @Override
    public void addUIUtils(UIUtils uiUtils) {
        this.uiUtils = uiUtils;
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

    public void setCurrentNutzer(Nutzer currentNutzer) {
        this.currentNutzer = currentNutzer;
    }

    public List<Listener> getListenerList() {
        return listenerList;
    }

    public void setListenerList(List<Listener> listenerList) {
        this.listenerList = listenerList;
    }

    public Konto getCurrentKonto() {
        return currentKonto;
    }

    public void setCurrentKonto(Konto currentKonto) {
        this.currentKonto = currentKonto;
    }
}
