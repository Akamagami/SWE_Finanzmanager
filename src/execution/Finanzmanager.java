package execution;

import backend.konten.TransaktionsVerwaltung;
import backend.speicher.Speicher;

public class Finanzmanager {
	//backend
	Speicher sp = new Speicher();
	TransaktionsVerwaltung TrVw = sp.getTrVw();
	//frontend
	
	
	public Finanzmanager() {
		
	}
	
}
