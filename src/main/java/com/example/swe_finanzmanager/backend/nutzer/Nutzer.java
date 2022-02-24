package com.example.swe_finanzmanager.backend.nutzer;

import java.util.List;


import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.SavableObject;
import com.example.swe_finanzmanager.backend.speicher.Speicher;

public class Nutzer implements SavableObject {

	private Name name;
	private int icon; //number will get changed translated to icon in UI
	private String id; // assigned by element factory
	
	
	public Nutzer(String vorname,String nachname, int icon, String id) {
		name = new Name(vorname, nachname);
		this.icon = icon;
		this.id = id;
	}


	@Override
	public String getSaveString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Name getName() {
		return name;
	}


	public int getIcon() {
		return icon;
	}


	public String getId() {
		return id;
	}


	@Override
	public String toString() {
		return id+"";
	}


	@Override
	public DataSet getXMLDataSet() {
		NutzerDataSet ret = new NutzerDataSet(name.getVorname(), name.getNachname(), icon);
		ret.addKey("id", id);
		return ret;
	}
	
}
