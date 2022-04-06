package com.example.swe_finanzmanager;

import com.example.swe_finanzmanager.backend.dataSets.KontoDataSet;
import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.dataSets.TransaktionDataSet;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.persistence.XMLAdapter;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.Speicher;
import com.example.swe_finanzmanager.constants.ClassType;
import com.example.swe_finanzmanager.frontend.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class MainApplication extends Application {

    private static MainController mainController = new MainController();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("startpage.fxml"));
        fxmlLoader.setController(mainController);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Speicher sp = new Speicher();
        sp.setDataAdapter(new XMLAdapter());

        /*------------------------------------------------------------------------*/


        mainController.setSp(sp);


        //sp.createAndAddTransaktion(t1);
        //System.out.println("Before:" + ko1.getKontostand());
        //sp.updateTrVw(Date.valueOf("2020-11-11"));
        //System.out.println("After:" + ko1.getKontostand());


        //sp.save();
        launch();
    }
}