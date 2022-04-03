package com.example.swe_finanzmanager.frontend.controller;

import com.example.swe_finanzmanager.backend.speicher.UIUtils;

import java.io.IOException;

public interface Controller {

    public void build() throws IOException;
    public void addUIUtils(UIUtils uiUtils);
}
