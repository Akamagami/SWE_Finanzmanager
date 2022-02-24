package com.example.swe_finanzmanager.frontend;

import com.example.swe_finanzmanager.MainApplication;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.Speicher;
import com.example.swe_finanzmanager.constants.ClassType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainController {

    private Speicher sp;

    @FXML
    ListView listViewStart;

    @FXML
    Parent root;

    @FXML
    public void chooseUser() throws IOException {
        Nutzer selectedNutzer = (Nutzer) listViewStart.getSelectionModel().getSelectedItem();
        System.out.println(selectedNutzer.getName().fullName());

        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("masterdetail.fxml"));
        MasterdetailController masterdetailController = new MasterdetailController();
        masterdetailController.setCurrentNutzer(selectedNutzer);

        loader.setController(masterdetailController);
        Scene masterDetailScene = new Scene(loader.load(), 720, 486);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(masterDetailScene);
        stage.show();
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
                try {
                    chooseUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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