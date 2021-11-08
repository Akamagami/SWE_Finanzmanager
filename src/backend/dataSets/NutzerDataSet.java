package backend.dataSets;

import backend.speicher.DataSet;
import constants.ClassType;

public class NutzerDataSet extends DataSet {

	public NutzerDataSet(String vorname, String nachname,int icon) {
		super(ClassType.NUTZER);
		this.getValues().put("vorname",vorname);
		this.getValues().put("nachname",nachname);
		this.getValues().put("icon",icon);
	}

}
