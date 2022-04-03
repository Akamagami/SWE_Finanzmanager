package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.io.IOException;

public class AddTransactionController implements Observable, Listener, Controller {

    private UIUtils uiUtils;

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

        ObservableList<Konto> observableList = FXCollections.observableList(uiUtils.getAllKonten());
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
        zielkonten.setItems(observableList);
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

    }
}
