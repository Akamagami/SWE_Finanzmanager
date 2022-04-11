package com.example.swe_finanzmanager.frontend.buttons;

import com.example.swe_finanzmanager.MainApplication;
import com.example.swe_finanzmanager.frontend.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTransaktionButton extends Button {

    public AddTransaktionButton(Controller controller) {
        this.setText("Hinzufügen");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("addTransactions.fxml"));
                try {
                    loader.setController(controller);
                    Parent newRoot = (Parent) loader.load();
                    controller.build();
                    Stage stage = new Stage();
                    stage.setTitle("Neue Transaktion hinzufügen");
                    stage.setScene(new Scene(newRoot));
                    stage.setResizable(false);
                    stage.show();

                } catch (IOException e) {
                }
            }
        });
    }
}
