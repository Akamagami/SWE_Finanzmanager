package com.example.swe_finanzmanager.backend.konten;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import com.example.swe_finanzmanager.backend.dataSets.KontoDataSet;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.SavableObject;

public class Konto implements SavableObject{

	private BigDecimal kontostand;	//initialer Kontostand wird nie ge�ndert
	//private Date datum;
	private Nutzer ersteller;
	
	private ArrayList<Nutzer> mitgliedList = new ArrayList<Nutzer>();
	
	private ArrayList<Transaktion> tList = new ArrayList<Transaktion>();
	
	private String name;
	private String beschreibung;
	private String id;
	private int icon;
	private boolean aktiv;
	
	public Konto(BigDecimal kontostand, Nutzer ersteller, String name, String beschreibung, int icon, String id) {
		super();
		this.kontostand = kontostand;
		this.ersteller = ersteller;
		this.name = name;
		this.beschreibung = beschreibung;
		this.id = id;
		this.icon = icon;
		this.aktiv = true;
		this.addMitglied(ersteller);
	}
	
	public void addMitglied(Nutzer n) {
		if(!mitgliedList.contains(n)) {
			mitgliedList.add(n);
		}
		
	}
	
	public void addTransaktion(Transaktion trkn) {
		tList.add(trkn);
		trkn.setZielKonto(this);
		//trkn.setAusgefuehrt(true);
	}
	
	
	
	/*public Date getDatum() {
		return datum;
	}*/

	public BigDecimal getInitKontostand() {
		return kontostand;
	}
	public BigDecimal getKontostand() {
		BigDecimal aktuellerKontostand = kontostand;
		for(Transaktion t:tList) {
			if(!t.isObsolet() && t.isAusgefuehrt()) {
				aktuellerKontostand = aktuellerKontostand.add(t.getBetrag());
			}
		}
		return aktuellerKontostand;
	}

	public Nutzer getErsteller() {
		return ersteller;
	}
	public ArrayList<Nutzer> getMitgliedList() {
		return mitgliedList;
	}

	public ArrayList<Transaktion> gettList() {
		ArrayList<Transaktion> ret = new ArrayList<Transaktion>();
		for(Transaktion t:tList) {
			if(!t.isObsolet()) {
				ret.add(t);
			}
		}
		return ret;
	}

	public ArrayList<Transaktion> getCompleteTList(){
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
	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}
	public boolean nutzerIstMitglied(Nutzer n) {
		return mitgliedList.contains(n);
	}
	public boolean removeMitglied(Nutzer n) {
		if(ersteller.equals(n)) {
			return false;
		}
		else {
			mitgliedList.remove(n);
			return true;
		}
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
