package backend.persistence;

import backend.speicher.Speicher;

public class XMLAdapter implements DataAdapter {

	private XMLWriter writer = new XMLWriter();
	private XMLReader reader = new XMLReader();
	
	public XMLAdapter() {
		
	}

	@Override
	public void writeAndSave(Speicher sp) {
		writer.writeAndSave(sp);
		
	}

	@Override
	public void readAndLoad(Speicher sp) {
		// TODO Auto-generated method stub
		
	}
	
}
