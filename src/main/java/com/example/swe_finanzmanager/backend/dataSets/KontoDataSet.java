package com.example.swe_finanzmanager.backend.dataSets;

import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.constants.ClassType;

public class KontoDataSet extends DataSet {

	public KontoDataSet(double kontostand, Nutzer ersteller, String name, String beschreibung, int icon) {
		////not an actual
		super(ClassType.KONTO);
		this.addKey("kontostand", kontostand);
		this.addKey("ersteller", ersteller);
		this.addKey("name", name);
		this.addKey("beschreibung", beschreibung);
		this.addKey("icon", icon);
	}
}
