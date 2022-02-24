package com.example.swe_finanzmanager.backend.persistence;

import com.example.swe_finanzmanager.backend.speicher.Speicher;

public interface DataAdapter {

	public void writeAndSave(Speicher sp);
	public void readAndLoad(Speicher sp);
	
}
