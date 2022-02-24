package backend.persistence;

import backend.speicher.Speicher;

public interface DataAdapter {

	public void writeAndSave(Speicher sp);
	public void readAndLoad(Speicher sp);
	
}
