package com.example.swe_finanzmanager.frontend.cellfactories;

import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.List;

public class MitgliederCellFactory implements Callback<ListView<Nutzer>, ListCell<Nutzer>> {
    @Override
    public ListCell<Nutzer> call(ListView<Nutzer> objektMitIdListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(Nutzer nutzer, boolean empty) {
                super.updateItem(nutzer, empty);
                if (empty || nutzer == null) {
                    setText(null);
                } else {
                    setText(nutzer.getName().fullName());
                }

                setOnMouseClicked((MouseEvent event) -> {
                        event.consume();
                });
            }
        };

    }
}


