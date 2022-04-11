package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.UIUtils;

import java.io.IOException;

public interface Controller extends Listener, Observable {

    public void build() throws IOException;

    public void addUIUtils(UIUtils uiUtils);

    public void setCurrentNutzer(Nutzer currentNutzer);

   public  void setCurrentKonto(Konto currentKonto);
}
