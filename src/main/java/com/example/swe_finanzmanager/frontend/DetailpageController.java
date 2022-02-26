package com.example.swe_finanzmanager.frontend;

import com.example.swe_finanzmanager.backend.konten.Konto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetailpageController {

    private Konto currentKonto;

    @FXML
    Label title;

    @FXML
    Label kontoStand;

    public void build() {
        title.setText(currentKonto.getName());
        kontoStand.setText(Double.toString(currentKonto.getKontostand()));

    }

    public Konto getCurrentKonto() {
        return currentKonto;
    }

    public void setCurrentKonto(Konto currentKonto) {
        this.currentKonto = currentKonto;
    }
}
