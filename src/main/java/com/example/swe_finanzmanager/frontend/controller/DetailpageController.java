package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.MainApplication;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;
import com.example.swe_finanzmanager.frontend.cellfactories.MitgliederCellFactory;
import com.example.swe_finanzmanager.frontend.cellfactories.TransaktionCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

public class DetailpageController implements com.example.swe_finanzmanager.frontend.controller.Observable, Listener, Controller {

    AddMitgliedButton addMitgliedButton;
    AddTransaktionButton addTransaktionButton;


    @FXML
    GridPane gridPane, infoPane, transaktionsPane, mitgliederPane;

    @FXML
    Label kontoname, kontostand, ersteller, id;

    @FXML
    ListView transaktionListView, mitgliederListView;


    private Konto currentKonto;
    private Nutzer currentNutzer;
    private List<Listener> listenerList = new ArrayList<>();
    private List<Label> labelList = Arrays.asList(kontoname, kontostand, ersteller, id);
    private UIUtils uiUtils;
    private AddTransactionController addTransactionController;
    private AddMitgliedController addMitgliedController;

    @Override
    public void build() throws IOException{
        try {
            addTransactionController = new AddTransactionController();
            addTransactionController.addListener(this);
            addTransactionController.addUIUtils(uiUtils);
            addTransactionController.setCurrentNutzer(currentNutzer);
            addTransactionController.setCurrentKonto(currentKonto);
            addMitgliedController = new AddMitgliedController();
            addMitgliedController.addListener(this);
            addMitgliedController.addUIUtils(uiUtils);
            addMitgliedController.setCurrentKonto(currentKonto);
            addTransaktionButton = new AddTransaktionButton(addTransactionController);
            transaktionsPane.add(addTransaktionButton, 0, 2);
            addMitgliedButton = new AddMitgliedButton(addMitgliedController);
            mitgliederPane.add(addMitgliedButton, 0, 2);

            kontoname.setText(currentKonto.getName());
            kontostand.setText(currentKonto.getKontostand().toString());
            ersteller.setText(currentKonto.getErsteller().getName().fullName());
            id.setText(currentKonto.getId());
            kontoname.setPadding(new Insets(5));
            kontostand.setPadding(new Insets(5));
            ersteller.setPadding(new Insets(5));
            id.setPadding(new Insets(5));


            transaktionListView.setPadding(new Insets(5));
            transaktionListView.setCellFactory(new TransaktionCellFactory());
            transaktionListView.setItems(FXCollections.observableList(currentKonto.gettList()));
            addTransaktionButton.setPadding(new Insets(5));
            transaktionListView.refresh();

            mitgliederListView.setPadding(new Insets(5));
            ObservableList<Nutzer> mitgliederObservableList = FXCollections.observableList(currentKonto.getMitgliedList());
            mitgliederListView.setCellFactory(new MitgliederCellFactory());
            mitgliederListView.setItems(mitgliederObservableList);
            mitgliederListView.setMouseTransparent(true);
            mitgliederListView.setFocusTraversable(false);
            addMitgliedButton.setPadding(new Insets(5));

            GridPane.setMargin(infoPane, new Insets(5));
            GridPane.setMargin(transaktionsPane, new Insets(10));
            GridPane.setMargin(mitgliederPane, new Insets(10));
            AnchorPane.setLeftAnchor(transaktionsPane, 150.0);

        } catch (Exception e) {
            buildEmpty();
        }

    }

    @Override
    public void addUIUtils(UIUtils uiUtils) {
        this.uiUtils = uiUtils;
    }

    public void buildEmpty() {
        gridPane.getChildren().clear();
        Label missingKontenInfo = new Label("Dieser Nutzer hat noch kein Konto eingerichtet!");
        missingKontenInfo.setPadding(new Insets(10));
        gridPane.add(missingKontenInfo, 0, 0);
    }

    public void clear() {
        //gridPane.getChildren().clear();
    }

    public Konto getCurrentKonto() {
        return currentKonto;
    }

    public void setCurrentKonto(Konto currentKonto) {
        this.currentKonto = currentKonto;
    }

    @Override
    public void update(com.example.swe_finanzmanager.frontend.controller.Observable observable) throws IOException {
        build();
    }

    @Override
    public void addListener(Listener listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listenerList.remove(listener);
    }

    @Override
    public void notifyListeners() throws IOException {
        for (Listener listener : listenerList) {
            listener.update(this);
        }
    }

    public Nutzer getCurrentNutzer() {
        return currentNutzer;
    }

    public void setCurrentNutzer(Nutzer currentNutzer) {
        this.currentNutzer = currentNutzer;
    }
}
