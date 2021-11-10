package backend.konten;

import java.util.ArrayList;
import java.util.Date;

public class transaktionsVerwaltung {

	Date aktuellesDatum;
	
	ArrayList<Transaktion> trPipeline = new ArrayList<Transaktion>();
	
	public transaktionsVerwaltung(Date datum) {
		aktuellesDatum = datum;
	}
	
	public void addTransaktion(Transaktion trkn) {
		if(!trkn.isAusgefuehrt()) {
			trPipeline.add(trkn);
		}
	}
	public void update() {
		for(Transaktion trkn: trPipeline) {
			if(trkn.getDatum().before(aktuellesDatum)) {
				trkn.getZielKonto().addTransaktion(trkn);
			}
		}
	}
}
