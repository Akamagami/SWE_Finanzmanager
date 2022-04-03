package com.example.swe_finanzmanager.frontend.cellfactories;

import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.util.List;

public class TransaktionCellFactory implements Callback<ListView<Transaktion>, ListCell<Transaktion>> {

    private static final String NEGATIVE_TRANSACTION = "derive(red, 50%)";
    private static final String POSITIVE_TRANSACTION = "derive(palegreen, 50%)";

    @Override
    public ListCell<Transaktion> call(ListView<Transaktion> objektMitIdListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(Transaktion transaktion, boolean empty) {
                super.updateItem(transaktion, empty);
                if (empty || transaktion == null) {
                    setText(null);
                } else {
                    setText(transaktion.getBetrag() + " - " + transaktion.getTitel() + " - " + transaktion.getDatum());
                    if (transaktion.getBetrag() < 0.0) {
                        setStyle("-fx-control-inner-background: " + NEGATIVE_TRANSACTION + ";");
                    } else {
                        setStyle("-fx-control-inner-background: " + POSITIVE_TRANSACTION + ";");
                    }
                }

                setOnMouseClicked((MouseEvent event) -> {
                    if (isEmpty()) {
                        event.consume();
                    }
                });

            }
        };

    }
}


