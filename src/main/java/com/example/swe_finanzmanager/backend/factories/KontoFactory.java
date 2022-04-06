package com.example.swe_finanzmanager.backend.factories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;

public class KontoFactory extends ElementFactory{

	@Override
	protected Object createObjectWithIndex(DataSet dataSet, String index) {

		
		Konto ret= new Konto((BigDecimal) dataSet.get("kontostand"),
							(Nutzer) dataSet.get("ersteller"), 
							(String) dataSet.get("name"),
							(String) dataSet.get("beschreibung"),
							(Integer) dataSet.get("icon"),
							index);
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
