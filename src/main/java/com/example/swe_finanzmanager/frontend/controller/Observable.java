package com.example.swe_finanzmanager.frontend.controller;

import java.io.IOException;

public interface Observable {

    public void addListener(Listener listener);

    public void removeListener(Listener listener);

    public void notifyListeners() throws IOException;
}
