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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

public class DetailpageController implements com.example.swe_finanzmanager.frontend.controller.Observable, Listener, Controller {


    @FXML
    GridPane gridPane, infoPane, transaktionsPane, mitgliederPane;

    @FXML
    Label kontoname, kontostand, ersteller, id;

    @FXML
    ListView transaktionListView, mitgliederListView;

    @FXML
    Button transaktionAddButton, mitgliederAddButton;


    private Konto currentKonto;
    private Nutzer currentNutzer;
    private List<Listener> listenerList = new ArrayList<>();
    private List<Label> labelList = Arrays.asList(kontoname, kontostand, ersteller, id);
    private UIUtils uiUtils;

    @Override
    public void build() throws IOException{
        try {
            kontoname.setText(currentKonto.getName());
            kontostand.setText(Double.toString(currentKonto.getKontostand()));
            ersteller.setText(currentKonto.getErsteller().getName().fullName());
            id.setText(currentKonto.getId());
            kontoname.setPadding(new Insets(5));
            kontostand.setPadding(new Insets(5));
            ersteller.setPadding(new Insets(5));
            id.setPadding(new Insets(5));

            transaktionListView.setPadding(new Insets(5));
            ObservableList<Transaktion> transaktionObservableList = FXCollections.observableList(currentKonto.gettList());
            transaktionListView.setCellFactory(new TransaktionCellFactory());
            transaktionListView.setItems(transaktionObservableList);
            transaktionAddButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("addTransactions.fxml"));
                    try {
                        AddTransactionController addTransactionController = new AddTransactionController();
                        addTransactionController.addUIUtils(uiUtils);
                        loader.setController(addTransactionController);
                        Parent newRoot = (Parent) loader.load();
                        addTransactionController.build();
                        Stage stage = new Stage();
                        stage.setTitle("Neue Transaktion hinzufügen");
                        stage.setScene(new Scene(newRoot));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            mitgliederListView.setPadding(new Insets(5));
            ObservableList<Nutzer> mitgliederObservableList = FXCollections.observableList(currentKonto.getMitgliedList());
            mitgliederListView.setCellFactory(new MitgliederCellFactory());
            mitgliederListView.setItems(mitgliederObservableList);
            mitgliederListView.setMouseTransparent(true);
            mitgliederListView.setFocusTraversable(false);

            GridPane.setMargin(infoPane, new Insets(5));
            GridPane.setMargin(transaktionsPane, new Insets(10));
            GridPane.setMargin(mitgliederPane, new Insets(10));
            AnchorPane.setLeftAnchor(transaktionsPane, 150.0);

        } catch (Exception e) {
            buildEmpty();
        }

        /*infoPane.add(name, 0, 0);
        infoPane.add(title, 1, 0);
        infoPane.add(kontoStandlabel, 0, 1);
        infoPane.add(kontoStand, 1, 1);
        infoPane.add(erstellerLabel, 0, 2);
        infoPane.add(ersteller, 1, 2);
        infoPane.add(idLabel, 0, 3);
        infoPane.add(id, 1, 3);*/

        //gridPane.add(infoPane, 0, 0);

        /*GridPane transaktionsPane = new GridPane();
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
        Button mitgliederAddButton = new Button("Mitglieder hinzufügen");
        mitgliederAddButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        ListView mitgliederListView = new ListView();
        mitgliederListView.setMaxSize(220, 140);
        mitgliederPane.add(mitgliederLabel, 0, 0);
        mitgliederPane.add(mitgliederListView, 0, 1);
        mitgliederPane.add(mitgliederAddButton, 0, 2);
        gridPane.add(mitgliederPane, 1, 1);
        ObservableList<Nutzer> mitgliederObservableList = FXCollections.observableList(currentKonto.getMitgliedList());

        mitgliederListView.setCellFactory(new MitgliederCellFactory());
        mitgliederListView.setItems(mitgliederObservableList);
        mitgliederListView.setMouseTransparent(true);
        mitgliederListView.setFocusTraversable(false);
        mitgliederListView.refresh();

        GridPane.setMargin(infoPane, new Insets(5));
        GridPane.setMargin(transaktionsPane, new Insets(10));
        GridPane.setMargin(mitgliederPane, new Insets(10));*/
    }

    @Override
    public void addUIUtils(UIUtils uiUtils) {
        this.uiUtils = uiUtils;
    }

    public void buildEmpty() {
        Label missingKontenInfo = new Label("Dieser Nutzer hat noch kein Konto eingerichtet!");
        gridPane.add(missingKontenInfo, 3, 3);
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
        clear();
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
