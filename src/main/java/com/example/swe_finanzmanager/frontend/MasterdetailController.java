package com.example.swe_finanzmanager.frontend;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MasterdetailController {

    private Nutzer currentNutzer;

    @FXML
    ListView masterdetailListView;

    public void initialize() {
        //ObservableList<Konto> kontoObservableList =FXCollections.observableList("To be implemented");
    }

    public Nutzer getCurrentNutzer() {
        return currentNutzer;
    }

    public void setCurrentNutzer(Nutzer currentNutzer) {
        this.currentNutzer = currentNutzer;
    }
}
