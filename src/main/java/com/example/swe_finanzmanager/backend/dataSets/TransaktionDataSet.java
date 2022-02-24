package com.example.swe_finanzmanager.backend.dataSets;

import java.sql.Date;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.constants.ClassType;

public class TransaktionDataSet extends DataSet {

	public TransaktionDataSet(double betrag, Date datum, Nutzer ersteller, Konto zielKonto, String beschreibung, String titel) {
		super(ClassType.TRANSAKTION);
		this.addKey("betrag",betrag);
		this.addKey("datum",datum);
		this.addKey("ersteller",ersteller);
		this.addKey("zielKonto",zielKonto);
		this.addKey("beschreibung",beschreibung);
		this.addKey("titel",titel);
	}
}
