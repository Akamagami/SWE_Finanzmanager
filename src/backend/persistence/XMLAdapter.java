package backend.persistence;

import backend.speicher.Speicher;

public class XMLAdapter implements DataAdapter {

	private String path =  "data.xml";
	
	private XMLWriter writer = new XMLWriter(path);
	private XMLReader reader = new XMLReader(path);
	
	
	
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
