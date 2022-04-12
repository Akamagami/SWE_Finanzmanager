package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

import java.io.IOException;

public class DetailTransactionController implements Controller {

    private Transaktion transaktion;

    @FXML
    Label nameLabel, name, beschreibungLabel, beschreibung, ausgefuehrtLabel, ausgefuehrt, betragLabel, betrag;


    @Override
    public void build() throws IOException {
        nameLabel.setPadding(new Insets(5));
        name.setPadding(new Insets(5));
        beschreibungLabel.setPadding(new Insets(5));
        beschreibung.setPadding(new Insets(5));
        ausgefuehrtLabel.setPadding(new Insets(5));
        ausgefuehrt.setPadding(new Insets(5));
        betragLabel.setPadding(new Insets(5));
        betrag.setPadding(new Insets(5));

        name.setText(transaktion.getTitel());
        betrag.setText(transaktion.getBetrag().toString());
        beschreibung.setText(transaktion.getBeschreibung());
        if (transaktion.isAusgefuehrt()) {
            ausgefuehrt.setText("Ausgeführt");
        } else {
            ausgefuehrt.setText("Nicht ausgeführt");
        }

    }

    @Override
    public void addUIUtils(UIUtils uiUtils) {

    }

    @Override
    public void setCurrentNutzer(Nutzer currentNutzer) {

    }

    @Override
    public void setCurrentKonto(Konto currentKonto) {

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

    public void setTransaktion(Transaktion transaktion) {
        this.transaktion = transaktion;
    }
}
