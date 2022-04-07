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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.sql.Date;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddTransactionController implements Observable, Listener, Controller {

    private UIUtils uiUtils;
    private Nutzer currentNutzer;
    private List<Listener> listenerList = new ArrayList<>();

    @FXML
    Label titelLabel, betragLabel, zielkontoLabel, beschreibungLabel;

    @FXML
    TextField titel, betrag, beschreibung;

    @FXML
    ChoiceBox<Konto> zielkonten;

    @FXML
    Button addNewTransaktionButton;

    @Override
    public void build() throws IOException {
        titelLabel.setPadding(new Insets(5));
        betragLabel.setPadding(new Insets(5));
        zielkontoLabel.setPadding(new Insets(5));
        beschreibungLabel.setPadding(new Insets(5));
        titel.setPadding(new Insets(5));
        betrag.setPadding(new Insets(5));
        zielkonten.setPadding(new Insets(5));
        beschreibung.setPadding(new Insets(5));

        zielkonten.setItems(FXCollections.observableList(uiUtils.getAllKonten()));
        zielkonten.setConverter(new StringConverter<Konto>() {
            @Override
            public String toString(Konto konto) {
                return konto.getName();
            }

            @Override
            public Konto fromString(String s) {
                return null;
            }
        });
        zielkonten.getSelectionModel().select(0);

        addNewTransaktionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                uiUtils.createTransaktion(new BigDecimal(betrag.getText()), new Date(System.currentTimeMillis()), currentNutzer, (Konto) zielkonten.getSelectionModel().getSelectedItem(), beschreibung.getText(), titel.getText());
                try {
                    notifyListeners();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) addNewTransaktionButton.getScene().getWindow();
                stage.close();
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
}
