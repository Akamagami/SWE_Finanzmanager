package backend.nutzer;

public class Name {

	private String vorname,nachname;
	
	public Name (String vorname, String nachname) {
		this.nachname = nachname;
		this.vorname = vorname;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}
	
	public String fullName() {
		return vorname + " " + nachname;
	}
	
}
