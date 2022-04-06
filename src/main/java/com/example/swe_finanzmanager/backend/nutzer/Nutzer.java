package com.example.swe_finanzmanager.backend.nutzer;

import java.util.List;


import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.SavableObject;
import com.example.swe_finanzmanager.backend.speicher.Speicher;

public class Nutzer extends SavableObject {

	private Name name;
	private int icon; //number will get changed translated to icon in UI
	
	
	public Nutzer(String vorname,String nachname, int icon, String id) {
		super(id);
		name = new Name(vorname, nachname);
		this.icon = icon;

	}


	
	public Name getName() {
		return name;
	}


	public int getIcon() {
		return icon;
	}



	@Override
	public String toString() {
		return this.getId()+"";
	}


	@Override
	public DataSet getXMLDataSet() {
		NutzerDataSet ret = new NutzerDataSet(name.getVorname(), name.getNachname(), icon);
		ret.addKey("id", this.getId());
		return ret;
	}
	
}
