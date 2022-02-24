package com.example.swe_finanzmanager.backend.dataSets;

import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.constants.ClassType;

public class NutzerDataSet extends DataSet {

	public NutzerDataSet(String vorname, String nachname,int icon) {
		super(ClassType.NUTZER);
		this.addKey("vorname",vorname);
		this.addKey("nachname",nachname);
		this.addKey("icon",icon);
	}

}
