package com.example.swe_finanzmanager.backend.factories;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;


public class TransaktionFactory extends ElementFactory {

	@Override
	protected Object createObjectWithIndex(DataSet dataSet, String index) {
		Transaktion ret= new Transaktion((BigDecimal) dataSet.get("betrag"),
							(Date) dataSet.get("datum"), 
							(Nutzer) dataSet.get("ersteller"),
							(Konto) dataSet.get("zielKonto"),
							(String) dataSet.get("beschreibung"),
							(String) dataSet.get("titel"),
							index);
		if(dataSet.hasKey("ausgefuehrt")) {
			ret.setAusgefuehrt((boolean) dataSet.get("ausgefuehrt"));
		}
		if(dataSet.hasKey("obsolet")) {
			ret.setAusgefuehrt((boolean) dataSet.get("obsolet"));
		}	
		return ret;
	}

}
