package com.example.swe_finanzmanager.backend.factories;


import java.sql.Date;
import java.util.Optional;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;


public class TransaktionFactory implements ElementFactory {

	int index = 1;
	
	@Override
	public String getIndex() {
		return index+"";
	}

	@Override
	public void setIndex(String id) {
		index = Integer.parseInt(id);

	}
	@Override
	public Object create(DataSet dataSet, Optional<String> optId) {
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
			if(Integer.parseInt(newIndex)+1 > index) {
				index = Integer.parseInt(newIndex)+1;
			}
		} else {
			newIndex = index+"";
			index++;
		}
		Transaktion ret= new Transaktion((double) dataSet.get("betrag"),
							(Date) dataSet.get("datum"), 
							(Nutzer) dataSet.get("ersteller"),
							(Konto) dataSet.get("zielKonto"),
							(String) dataSet.get("beschreibung"),
							(String) dataSet.get("titel"),
							newIndex);
		if(dataSet.hasKey("ausgefuehrt")) {
			ret.setAusgefuehrt((boolean) dataSet.get("ausgefuehrt"));
		}
		if(dataSet.hasKey("obsolet")) {
			ret.setAusgefuehrt((boolean) dataSet.get("obsolet"));
		}	
		return ret;
	}

}
