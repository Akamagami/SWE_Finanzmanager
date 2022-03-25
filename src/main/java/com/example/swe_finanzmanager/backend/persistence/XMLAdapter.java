package com.example.swe_finanzmanager.backend.persistence;

import com.example.swe_finanzmanager.backend.speicher.Speicher;

public class XMLAdapter extends DataAdapter {

	String endPath = ".xml";
	
	public XMLAdapter() {
		
	}
	@Override
	protected void write(String path,Speicher sp) {
		 XMLWriter writer = new XMLWriter(path+""+endPath);
		 writer.writeAndSave(sp);
	}
	@Override
	protected void read(String path, Speicher sp) {
		XMLReader reader = new XMLReader(path+""+endPath);
		reader.readAndLoad(sp);
	}

	
}
