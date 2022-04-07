package com.example.swe_finanzmanager;

import com.example.swe_finanzmanager.backend.dataSets.KontoDataSet;
import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.dataSets.TransaktionDataSet;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
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
        //sp.setDataAdapter(new XMLAdapter());

        DataSet N1 = new NutzerDataSet("Hi", "Work", 2);

        Nutzer n1 = (Nutzer) sp.createObject(N1);
        System.out.println("Nutzer:" + n1.toString());


        DataSet N2 = new NutzerDataSet("Not", "Cool", 4);
        n1 = (Nutzer) sp.createObject(N2);

        for(Nutzer n:(List<Nutzer>)(List<?>) sp.getAll(ClassType.NUTZER)) {
            System.out.println("nnutzer:" + n.toString());
        }
        /*------------------------------------------------------------------------*/

        DataSet K1 = new KontoDataSet(100.23,(Nutzer) sp.getObject(ClassType.NUTZER, "2"),"Konto1","Beeee",2);
        Konto ko1 = (Konto) sp.createObject(K1);
        ko1.addMitglied((Nutzer) sp.getObject(ClassType.NUTZER, "1"));
        ko1.addMitglied(n1);
        for(Konto n:(List<Konto>)(List<?>) sp.getAll(ClassType.KONTO)) {
            System.out.println("Konto:" + n.toString());
        }

        TransaktionDataSet t1 = new TransaktionDataSet(100.0, Date.valueOf("2020-11-10"), n1, ko1, "Schutzgeld", "Merci");
        System.out.println("Erzeugung funktioniret");


        mainController.setSp(sp);


        //sp.createAndAddTransaktion(t1);
        //System.out.println("Before:" + ko1.getKontostand());
        //sp.updateTrVw(Date.valueOf("2020-11-11"));
        //System.out.println("After:" + ko1.getKontostand());


        //sp.save();
        launch();
    }
}