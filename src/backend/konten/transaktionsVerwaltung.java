package backend.konten;

import java.util.ArrayList;
import java.util.Date;

public class transaktionsVerwaltung {

	Date aktuellesDatum;
	
	ArrayList<Transaktion> trPipeline = new ArrayList<Transaktion>();
	
	public transaktionsVerwaltung(Date datum) {
		aktuellesDatum = datum;
	}
	
	
}
