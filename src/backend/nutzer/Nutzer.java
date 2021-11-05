package backend.nutzer;

import backend.speicher.SavableObject;

public class Nutzer implements SavableObject{

	private Name name;
	private int icon; //number will get changed translated to icon in UI
	private int id; // assigned by element factory
	
	
	public Nutzer(String Name) {
		
	}


	@Override
	public String getSaveString() {
		// TODO Auto-generated method stub
		return null;
	}
}
