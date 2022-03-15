package com.example.swe_finanzmanager.backend.factories;

import java.util.List;
import java.util.Optional;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;

public class KontoFactory implements ElementFactory{

	int index = 1;
	
	@Override
	public String getIndex() {
		return index + "";
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
		
		Konto ret= new Konto((double) dataSet.get("kontostand"),
							(Nutzer) dataSet.get("ersteller"), 
							(String) dataSet.get("name"),
							(String) dataSet.get("beschreibung"),
							(Integer) dataSet.get("icon"),
							newIndex);
		if(dataSet.hasKey("aktiv")) {
			ret.setAktiv((boolean) dataSet.get("aktiv"));
		}
		if(dataSet.hasKey("mitgliedList")) {
			List<Nutzer> tmp = (List<Nutzer>) dataSet.get("mitgliedList");
			for(Nutzer n:tmp) {
				ret.addMitglied(n);
			}
		}
		if(dataSet.hasKey("tList")) {
			List<Transaktion> tmp = (List<Transaktion>) dataSet.get("tList");
			for(Transaktion t:tmp) {
				ret.addTransaktion(t);
			}
		}
		return ret;
	}

}
