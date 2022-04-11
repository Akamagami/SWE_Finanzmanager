package com.example.swe_finanzmanager.frontend.buttons;

import com.example.swe_finanzmanager.MainApplication;
import com.example.swe_finanzmanager.frontend.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddKontoButton extends Button {

    public AddKontoButton(Controller controller) {
        this.setText("Konto hinzufügen");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("addKonto.fxml"));
                try {
                    loader.setController(controller);
                    Parent newRoot = (Parent) loader.load();
                    controller.build();
                    Stage stage = new Stage();
                    stage.setTitle("Neues Konto erstellen");
                    stage.setScene(new Scene(newRoot));
                    stage.setResizable(false);
                    stage.show();

                } catch (Exception e) {
                }
            }
        });
    }
}
