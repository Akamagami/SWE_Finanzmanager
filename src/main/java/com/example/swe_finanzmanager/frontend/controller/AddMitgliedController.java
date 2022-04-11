package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddMitgliedController implements Controller {

    private List<Listener> listenerList = new ArrayList<>();
    private UIUtils uiUtils;
    private Konto currentKonto;

    @FXML
    Button addNewMitgliedButton;

    @FXML
    ChoiceBox<Nutzer> mitgliederAuswahl;

    @FXML
    GridPane mitgliedFormGridPane;

    @Override
    public void build() throws IOException {
        mitgliedFormGridPane.setPadding(new Insets(5));

        mitgliederAuswahl.setItems(FXCollections.observableList(uiUtils.getAllNutzer()));
        mitgliederAuswahl.setConverter(new StringConverter<Nutzer>() {
            @Override
            public String toString(Nutzer nutzer) {
                return nutzer.getName().fullName();
            }

            @Override
            public Nutzer fromString(String s) {
                return null;
            }
        });
        mitgliederAuswahl.getSelectionModel().select(0);

        addNewMitgliedButton.setPadding(new Insets(5));
        addNewMitgliedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentKonto.addMitglied((Nutzer) mitgliederAuswahl.getSelectionModel().getSelectedItem());
                try {
                    notifyListeners();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) addNewMitgliedButton.getScene().getWindow();
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

    }

    @Override
    public void update(Observable observable) throws IOException {
        for (Listener listener : listenerList) {
            listener.update(this);
        }
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

    public List<Listener> getListenerList() {
        return listenerList;
    }

    public void setListenerList(List<Listener> listenerList) {
        this.listenerList = listenerList;
    }

    public UIUtils getUiUtils() {
        return uiUtils;
    }

    public void setUiUtils(UIUtils uiUtils) {
        this.uiUtils = uiUtils;
    }

    public Konto getCurrentKonto() {
        return currentKonto;
    }

    public void setCurrentKonto(Konto currentKonto) {
        this.currentKonto = currentKonto;
    }
}
