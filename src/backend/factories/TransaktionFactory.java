package backend.factories;

import java.sql.Date;
import java.util.Optional;

import backend.konten.Konto;
import backend.konten.Transaktion;
import backend.nutzer.Nutzer;
import backend.speicher.DataSet;
import backend.speicher.ElementFactory;

public class TransaktionFactory implements ElementFactory {

	int index = 1;
	
	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public void setIndex(String id) {
		index = Integer.parseInt(id);

	}
	@Override
	public Object create(DataSet dataSet, Optional<String> optId) {
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
			if(Integer.parseInt(newIndex)+1 > index) {
				index = Integer.parseInt(newIndex)+1;
			}
		} else {
			newIndex = index+"";
			index++;
		}
		
		Transaktion ret= new Transaktion((double) dataSet.get("betrag"),
							(Date) dataSet.get("datum"), 
							(Nutzer) dataSet.get("ersteller"),
							(Konto) dataSet.get("zielkonto"),
							(String) dataSet.get("beschreibung"),
							(String) dataSet.get("titel"),
							newIndex);
		
		
		return ret;
	}

}
