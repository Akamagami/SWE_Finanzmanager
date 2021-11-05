package backend.factories;

import java.util.Optional;

import backend.nutzer.Nutzer;
import backend.speicher.DataSet;
import backend.speicher.ElementFactory;



public class NutzerFactory implements ElementFactory {

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
		
		Nutzer ret= new Nutzer((String) dataSet.get("vorname"),
							(String) dataSet.get("nachname"), 
							(Integer) dataSet.get("icon"),
							newIndex);
		return ret;
	}

}
