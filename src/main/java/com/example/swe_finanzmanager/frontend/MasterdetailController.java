package com.example.swe_finanzmanager.frontend;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.PopupWindow;

public class MasterdetailController {

    private Nutzer currentNutzer;
    private UIUtils uiUtils;

    @FXML
    ListView masterdetailListView;

    @FXML
    DetailpageController detailpageController;

    @FXML
    Pane detailpage;

    public void initialize() {
        ObservableList<Konto> kontenObservableList = FXCollections.observableList(uiUtils.getNutzerKonten(currentNutzer));
        masterdetailListView.setCellFactory(new KontoCellFactory());
        masterdetailListView.setItems(kontenObservableList);
        masterdetailListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Konto selectedKonto = (Konto) masterdetailListView.getSelectionModel().getSelectedItem();
                detailpageController.setCurrentKonto(selectedKonto);
                detailpageController.build();
            }
        });
        masterdetailListView.refresh();
        masterdetailListView.setPrefHeight(1270.0);
        AnchorPane.setTopAnchor(masterdetailListView, 3.0);
        AnchorPane.setLeftAnchor(masterdetailListView, 4.0);
        AnchorPane.setBottomAnchor(masterdetailListView, 3.0);
        AnchorPane.setLeftAnchor(detailpage, 260.0);

        System.out.println("Test");
        detailpageController.setCurrentKonto(uiUtils.getNutzerKonten(currentNutzer).get(0));
    }

    @FXML
    public void chosenKonto() {

    }

    public Nutzer getCurrentNutzer() {
        return currentNutzer;
    }

    public void setCurrentNutzer(Nutzer currentNutzer) {
        this.currentNutzer = currentNutzer;
    }

    public UIUtils getUiUtils() {
        return uiUtils;
    }

    public void setUiUtils(UIUtils uiUtils) {
        this.uiUtils = uiUtils;
    }
}
