package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.MainApplication;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.Speicher;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;
import com.example.swe_finanzmanager.frontend.cellfactories.NutzerCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainController {

    private UIUtils uiUtils;
    private Speicher speicher;

    @FXML
    ListView listViewStart;

    @FXML
    Parent root;

    @FXML
    public void chooseUser() throws IOException {
        Nutzer selectedNutzer = (Nutzer) listViewStart.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("masterdetail.fxml"));
        MasterdetailController masterdetailController = new MasterdetailController();
        masterdetailController.setCurrentNutzer(selectedNutzer);
        masterdetailController.addUIUtils(uiUtils);

        loader.setController(masterdetailController);
        Scene masterDetailScene = new Scene(loader.load(), 1000, 350);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(masterDetailScene);
        stage.setResizable(false);
        stage.show();

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primaryScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primaryScreenBounds.getHeight() - stage.getHeight()) / 2);
        masterdetailController.setStage(stage);
        masterdetailController.build();
    }

    public void initialize() {
        List<Nutzer> nutzerList = uiUtils.getAllNutzer();
        ObservableList<Nutzer> nutzerObservableList = FXCollections.observableList(nutzerList);
        listViewStart.setCellFactory(new NutzerCellFactory());
        listViewStart.setItems(nutzerObservableList);
        listViewStart.refresh();
        listViewStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    chooseUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UIUtils getUiUtils() {
        return uiUtils;
    }

    public void setUiUtils(UIUtils uiUtils) {
        this.uiUtils = uiUtils;
    }

    public Speicher getSpeicher() {
        return speicher;
    }

    public void setSpeicher(Speicher speicher) {
        this.speicher = speicher;
    }
}