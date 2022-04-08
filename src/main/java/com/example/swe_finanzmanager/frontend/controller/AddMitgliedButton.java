package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.MainApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AddMitgliedButton extends Button {

    public AddMitgliedButton(Controller controller) {
        this.setText("Hinzufügen");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("addMitglieder.fxml"));
                try {
                    loader.setController(controller);
                    Parent newRoot = (Parent) loader.load();
                    controller.build();
                    Stage stage = new Stage();
                    stage.setTitle("Neues Mitglied hinzufügen");
                    stage.setScene(new Scene(newRoot));
                    stage.setResizable(false);
                    stage.show();

                } catch (IOException e) {
                }
            }
        });
    }
}
