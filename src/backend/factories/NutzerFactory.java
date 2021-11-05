package backend.factories;

import java.util.Optional;

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
	public Object create(Object[] params, Optional<String> optId) {
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
		Nutzer ret = new Nutzer((String) params[0],(String) params[1],newIndex);
		return ret;
	}

}
