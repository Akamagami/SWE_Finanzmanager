package com.example.swe_finanzmanager.frontend;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class DetailpageController {

    private Konto currentKonto;


    @FXML
    GridPane gridPane;


    public void build() {
        gridPane.getChildren().clear();

        GridPane infoPane = new GridPane();
        Label name = new Label("Kontoname");
        name.setPadding(new Insets(5));
        Label title = new Label(currentKonto.getName());
        title.setPadding(new Insets(5));
        Label kontoStandlabel = new Label("Kontostand");
        kontoStandlabel.setPadding(new Insets(5));
        Label kontoStand = new Label(Double.toString(currentKonto.getKontostand()));
        kontoStand.setPadding(new Insets(5));
        Label erstellerLabel = new Label("Ersteller");
        erstellerLabel.setPadding(new Insets(5));
        Label ersteller = new Label(currentKonto.getErsteller().getName().fullName());
        ersteller.setPadding(new Insets(5));
        Label idLabel = new Label("Konto-ID");
        idLabel.setPadding(new Insets(5));
        Label id = new Label(currentKonto.getId());
        id.setPadding(new Insets(5));


        infoPane.add(name, 0, 0);
        infoPane.add(title, 1, 0);
        infoPane.add(kontoStandlabel, 0, 1);
        infoPane.add(kontoStand, 1, 1);
        infoPane.add(erstellerLabel, 0, 2);
        infoPane.add(ersteller, 1, 2);
        infoPane.add(idLabel, 0, 3);
        infoPane.add(id, 1, 3);

        gridPane.add(infoPane, 0, 0);

        GridPane transaktionsPane = new GridPane();
        Label transaktionsLabel = new Label("Transaktionen");
        ListView transaktionListView = new ListView();
        transaktionListView.setMaxSize(220, 140);
        transaktionsPane.add(transaktionsLabel, 0 ,0);
        transaktionsPane.add(transaktionListView, 0, 1);
        gridPane.add(transaktionsPane, 1, 0);
        ObservableList<Transaktion> transaktionObservableList = FXCollections.observableList(currentKonto.gettList());
        transaktionListView.setCellFactory(new TransaktionCellFactory());
        transaktionListView.setItems(transaktionObservableList);
        transaktionListView.refresh();

        GridPane mitgliederPane = new GridPane();
        Label mitgliederLabel = new Label("Mitglieder");
        ListView mitgliederListView = new ListView();
        mitgliederListView.setMaxSize(220, 140);
        mitgliederPane.add(mitgliederLabel, 0, 0);
        mitgliederPane.add(mitgliederListView, 0, 1);
        gridPane.add(mitgliederPane, 1, 1);
        ObservableList<Nutzer> mitgliederObservableList = FXCollections.observableList(currentKonto.getMitgliedList());
        mitgliederListView.setCellFactory(new MitgliederCellFactory());
        mitgliederListView.setItems(mitgliederObservableList);
        mitgliederListView.refresh();

        GridPane.setMargin(infoPane, new Insets(5));
        GridPane.setMargin(transaktionsPane, new Insets(10));
        GridPane.setMargin(mitgliederPane, new Insets(10));
    }

    public Konto getCurrentKonto() {
        return currentKonto;
    }

    public void setCurrentKonto(Konto currentKonto) {
        this.currentKonto = currentKonto;
    }
}
