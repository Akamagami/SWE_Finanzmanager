package com.example.swe_finanzmanager.backend.persistence;

import com.example.swe_finanzmanager.backend.speicher.Speicher;

public abstract class DataAdapter {

	String path = "finanzmanagerData";
	
	public void writeAndSave(Speicher sp) {
		this.write(path, sp);
	}
	public void readAndLoad(Speicher sp) {
		this.read(path, sp);
	}
	
	protected void write(String path,Speicher sp) {
		
	}
	protected void read(String path, Speicher sp) {
		
	}
	
}
