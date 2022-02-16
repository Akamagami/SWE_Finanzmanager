package backend.konten;

import java.util.ArrayList;
import java.sql.Date;

import backend.nutzer.Nutzer;
import backend.speicher.DataSet;
import backend.speicher.SavableObject;
import backend.dataSets.*;

public class Konto implements SavableObject{

	private double kontostand;	
	private Date datum;
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
		trkn.setAusgefuehrt(true);
	}
	
	public void updateKontostand(Date datum) {
		int current = 0;
		for(Transaktion trkn: tList) {
			if(trkn.getDatum().before(datum)) {
				current += trkn.getBetrag();
			}
		}
		kontostand = current;
		this.datum = datum;
	}
	
	public Date getDatum() {
		return datum;
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
	public boolean nutzerIstMitglied(Nutzer n) {
		return mitgliedList.contains(n);
	}

	@Override
	public String toString() {
		return id+"";
	}

	@Override
	public String getSaveString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSet getXMLDataSet() {
		DataSet ret = new KontoDataSet(kontostand, ersteller, name, beschreibung, icon);
		ret.addKey("id", id);
		ret.addKey("aktiv", aktiv);
		ret.addKey("mitgliedList", mitgliedList);
		ret.addKey("tList", tList);
		return ret;
	}

	
}
