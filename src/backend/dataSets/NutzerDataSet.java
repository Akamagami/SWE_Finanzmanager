package backend.dataSets;

import backend.speicher.DataSet;
import constants.ClassType;

public class NutzerDataSet extends DataSet {

	public NutzerDataSet(String vorname, String nachname,int icon) {
		super(ClassType.NUTZER);
		this.addKey("vorname",vorname);
		this.addKey("nachname",nachname);
		this.addKey("icon",icon);
	}

}
