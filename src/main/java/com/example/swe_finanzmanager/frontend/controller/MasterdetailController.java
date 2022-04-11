package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.MainApplication;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;
import com.example.swe_finanzmanager.frontend.cellfactories.KontoCellFactory;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MasterdetailController implements Controller {

    private Nutzer currentNutzer;
    private UIUtils uiUtils;
    private Stage stage;
    private List<Listener> listenerList = new ArrayList<>();
    private Controller detailpageController;

    @FXML
    AnchorPane masterdetailAP;

    @FXML
    ListView masterdetailListView;

    @FXML
    Pane detailpage;

    @Override
    public void build() throws IOException {
        detailpageController = new DetailpageController();
        listenerList.add(detailpageController);
        detailpageController.addListener(this);


        stage.setTitle(currentNutzer.getName().fullName());
        masterdetailListView.getItems().clear();
        masterdetailListView.setPrefHeight(1270.0);
        AnchorPane.setTopAnchor(masterdetailListView, 3.0);
        AnchorPane.setLeftAnchor(masterdetailListView, 4.0);
        AnchorPane.setBottomAnchor(masterdetailListView, 50.0);
        AnchorPane.setLeftAnchor(detailpage, 260.0);


        MenuButton changeNutzer = new MenuButton("Nutzer wechseln");
        for (Nutzer nutzer : uiUtils.getAllNutzer()) {
            MenuItem item = new MenuItem(nutzer.getName().fullName());
            item.setId(nutzer.getId());
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Nutzerwechsel");
                    MenuItem source = (MenuItem) event.getSource();
                    setCurrentNutzer(uiUtils.getNutzer(source.getId()));
                    System.out.println(getCurrentNutzer().getName().fullName());
                    try {
                        build();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            changeNutzer.getItems().add(item);
        }
        changeNutzer.setPrefHeight(40);
        changeNutzer.setPrefWidth(200);
        changeNutzer.setVisible(true);
        changeNutzer.setPopupSide(Side.TOP);
        AnchorPane.setBottomAnchor(changeNutzer, 5.0);
        AnchorPane.setLeftAnchor(changeNutzer, 5.0);


        masterdetailAP.getChildren().add(changeNutzer);
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("detailpage.fxml"));
        loader.setController(detailpageController);
        Parent loadedElement = loader.load();


        try {
            ObservableList<Konto> kontenObservableList = FXCollections.observableList(uiUtils.getNutzerKonten(currentNutzer));
            masterdetailListView.setCellFactory(new KontoCellFactory());
            masterdetailListView.setItems(kontenObservableList);
            masterdetailListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Konto selectedKonto = (Konto) masterdetailListView.getSelectionModel().getSelectedItem();
                    detailpageController.setCurrentKonto(selectedKonto);
                    try {
                        notifyListeners();
                    } catch (IOException e) {
                        buildEmpty();
                    }
                }
            });
            masterdetailListView.refresh();
            detailpageController.setCurrentKonto(uiUtils.getNutzerKonten(currentNutzer).get(0));
            detailpageController.setCurrentNutzer(currentNutzer);
            detailpageController.addUIUtils(uiUtils);
            detailpageController.build();
        } catch (Exception e) {
        }

        notifyListeners();
        detailpage.getChildren().clear();
        detailpage.getChildren().add(loadedElement);
    }

    @Override
    public void addUIUtils(UIUtils uiUtils) {
        this.uiUtils = uiUtils;
    }

    public void clear() {
        masterdetailListView.getItems().clear();
    }

    public void buildEmpty() {
        detailpage.getChildren().clear();
        detailpage.getChildren().add(new Label("Dieser Nutzer hat leider noch kein Konto angelegt"));
    }

    public Nutzer getCurrentNutzer() {
        return currentNutzer;
    }

    public void setCurrentNutzer(Nutzer currentNutzer) {
        this.currentNutzer = currentNutzer;
    }

    @Override
    public void setCurrentKonto(Konto currentKonto) {

    }

    public UIUtils getUiUtils() {
        return uiUtils;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void addListener(Listener listener) {
        listenerList.add(listener);
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

    @Override
    public void update(Observable observable) throws IOException {
        this.build();
    }
}
