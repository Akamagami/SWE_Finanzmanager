package com.example.swe_finanzmanager.frontend;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class MasterdetailController {

    private Nutzer currentNutzer;
    private UIUtils uiUtils;
    private Stage stage;

    @FXML
    AnchorPane masterdetailAP;

    @FXML
    ListView masterdetailListView;

    @FXML
    DetailpageController detailpageController;

    @FXML
    Pane detailpage;

    public void build() {
        System.out.println("Build new Mastedetail!");
        stage.setTitle(currentNutzer.getName().fullName());
        masterdetailListView.getItems().clear();
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
                    build();
                }
            });
            changeNutzer.getItems().add(item);
        }
        changeNutzer.setPrefHeight(40);
        changeNutzer.setPrefWidth(200);
        changeNutzer.setVisible(true);
        masterdetailAP.getChildren().add(changeNutzer);
        AnchorPane.setBottomAnchor(changeNutzer, 5.0);
        AnchorPane.setLeftAnchor(changeNutzer, 5.0);



        detailpageController.setCurrentKonto(uiUtils.getNutzerKonten(currentNutzer).get(0));
    }

    public void clear() {
        masterdetailListView.getItems().clear();
        detailpageController.clear();
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
