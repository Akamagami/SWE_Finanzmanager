package com.example.swe_finanzmanager.backend.factories;

import java.util.Optional;

import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;

public class NutzerFactory extends ElementFactory {

	int index = 1;
	
	@Override
	protected Object createObjectWithIndex(DataSet dataSet, String index) {
		Nutzer ret= new Nutzer((String) dataSet.get("vorname"),
							(String) dataSet.get("nachname"), 
							(Integer) dataSet.get("icon"),
							index);
		return ret;
	}

}
