package com.example.swe_finanzmanager.backend.konten;

import java.sql.Date;
import java.util.ArrayList;

public class TransaktionsVerwaltung {

	Date aktuellesDatum;
	
	ArrayList<Transaktion> trPipeline = new ArrayList<Transaktion>();
	
	public TransaktionsVerwaltung(Date datum) {
		this.update(datum);
	}
	
	public void addTransaktion(Transaktion trkn) {
		if(!trkn.isAusgefuehrt()) {
			trPipeline.add(trkn);
			this.update();
		}
	}
	public void update() {
		for(Transaktion trkn: trPipeline) {
			if(!trkn.isAusgefuehrt() && (trkn.getDatum().before(aktuellesDatum) || trkn.getDatum().equals(aktuellesDatum))) {
				trkn.getZielKonto().addTransaktion(trkn);			
			}
		}
	}
	public void update(Date datum) {
		aktuellesDatum = datum;
		this.update();
	}
}
