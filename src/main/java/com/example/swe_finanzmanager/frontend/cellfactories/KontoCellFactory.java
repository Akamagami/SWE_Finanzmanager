package com.example.swe_finanzmanager.frontend.cellfactories;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.List;

public class KontoCellFactory implements Callback<ListView<Konto>, ListCell<Konto>> {
    @Override
    public ListCell<Konto> call(ListView<Konto> objektMitIdListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(Konto konto, boolean empty) {
                super.updateItem(konto, empty);
                if (empty || konto == null) {
                    setText(null);
                } else {
                    setText(konto.getName());
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


