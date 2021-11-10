package backend.dataSets;

import backend.nutzer.Nutzer;
import backend.speicher.DataSet;
import constants.ClassType;

public class KontoDataSet extends DataSet {

	public KontoDataSet(double kontostand, Nutzer ersteller, String name, String beschreibung, int icon) {
		super(ClassType.KONTO);
		this.addKey("kontostand", kontostand);
		this.addKey("ersteller", ersteller);
		this.addKey("name", name);
		this.addKey("beschreibung", beschreibung);
		this.addKey("icon", icon);
	}
}
