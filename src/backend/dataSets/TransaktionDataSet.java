package backend.dataSets;

import java.sql.Date;

import backend.konten.Konto;
import backend.nutzer.Nutzer;
import backend.speicher.DataSet;
import constants.ClassType;

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
