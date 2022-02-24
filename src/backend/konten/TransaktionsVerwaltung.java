package backend.konten;

import java.util.ArrayList;
import java.sql.Date;

public class TransaktionsVerwaltung {

	Date aktuellesDatum;
	
	ArrayList<Transaktion> trPipeline = new ArrayList<Transaktion>();
	
	public TransaktionsVerwaltung(Date datum) {
		aktuellesDatum = datum;
	}
	
	public void addTransaktion(Transaktion trkn) {
		if(!trkn.isAusgefuehrt()) {
			trPipeline.add(trkn);
		}
	}
	public void update() {
		for(Transaktion trkn: trPipeline) {
			if(!trkn.isAusgefuehrt() && trkn.getDatum().before(aktuellesDatum)) {
				trkn.getZielKonto().addTransaktion(trkn);			
			}
		}
	}
	public void update(Date datum) {
		aktuellesDatum = datum;
		this.update();
	}
}
