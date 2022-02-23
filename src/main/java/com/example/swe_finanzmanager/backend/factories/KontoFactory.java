package com.example.swe_finanzmanager.backend.factories;


import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;

import java.util.Optional;

public class KontoFactory implements ElementFactory {

	int index = 1;
	
	@Override
	public int getIndex() {
		return index;
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
		return ret;
	}

}
