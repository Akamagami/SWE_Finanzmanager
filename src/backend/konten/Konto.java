package backend.konten;

import java.util.ArrayList;

import backend.nutzer.Nutzer;
import backend.speicher.SavableObject;

public class Konto implements SavableObject{

	private double kontostand;	
	
	private Nutzer ersteller;
	private ArrayList<Nutzer> mitgliedList = new ArrayList<Nutzer>();
	
	private ArrayList<Transaktion> tList = new ArrayList<Transaktion>();
	
	private String name;
	private String beschreibung;
	private String id;
	private int icon;
	private boolean aktiv;
	
	public Konto(double kontostand, Nutzer ersteller, String name, String beschreibung, int icon, String id) {
		super();
		this.kontostand = kontostand;
		this.ersteller = ersteller;
		this.name = name;
		this.beschreibung = beschreibung;
		this.id = id;
		this.icon = icon;
		this.aktiv = true;
	}
	
	public void addMitglied(Nutzer n) {
		mitgliedList.add(n);
	}
	
	public void addTransaktion(Transaktion trkn) {
		tList.add(trkn);
	}
	
	public double getKontostand() {
		return kontostand;
	}

	public Nutzer getErsteller() {
		return ersteller;
	}

	public ArrayList<Nutzer> getMitgliedList() {
		return mitgliedList;
	}

	public ArrayList<Transaktion> gettList() {
		return tList;
	}

	public String getName() {
		return name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public String getId() {
		return id;
	}

	public int getIcon() {
		return icon;
	}

	public boolean isAktiv() {
		return aktiv;
	}

	@Override
	public String toString() {
		return "Konto [kontostand=" + kontostand + ", ersteller=" + ersteller + ", mitgliedList=" + mitgliedList
				+ ", tList=" + tList + ", name=" + name + ", beschreibung=" + beschreibung + ", id=" + id + ", icon="
				+ icon + ", aktiv=" + aktiv + "]";
	}

	@Override
	public String getSaveString() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
