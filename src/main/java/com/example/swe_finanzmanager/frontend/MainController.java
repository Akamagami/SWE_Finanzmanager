package com.example.swe_finanzmanager.frontend;

import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.Speicher;
import com.example.swe_finanzmanager.constants.ClassType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class MainController {

    private Speicher sp;

    @FXML
    ListView listViewStart;

    @FXML
    public void chooseUser() {
        Nutzer selectedNutzer = (Nutzer) listViewStart.getSelectionModel().getSelectedItem();
        System.out.println(selectedNutzer);
    }

    public void initialize() {
        List<Object> nutzerList = sp.getAll(ClassType.NUTZER);
        ObservableList<Nutzer> nutzerObservableList = FXCollections.observableList((List<Nutzer>) (List<?>) nutzerList);
        listViewStart.setCellFactory(new NutzerCellFactory());
        listViewStart.setItems(nutzerObservableList);
        listViewStart.refresh();
        listViewStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                chooseUser();
            }
        });
    }

    public Speicher getSp() {
        return sp;
    }

    public void setSp(Speicher sp) {
        this.sp = sp;
    }
}