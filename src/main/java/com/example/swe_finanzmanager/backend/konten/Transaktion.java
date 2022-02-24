package com.example.swe_finanzmanager.backend.konten;

import java.sql.Date;

import com.example.swe_finanzmanager.backend.dataSets.TransaktionDataSet;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.SavableObject;

public class Transaktion implements SavableObject {

	private double betrag;
	private Date datum;
	private boolean obsolet;
	private String beschreibung;
	private String titel;
	private String id;
	private boolean ausgefuehrt;
	
	private Nutzer ersteller;
	private Konto zielKonto;
	
	public Transaktion(double betrag, Date datum, Nutzer ersteller, Konto zielKonto,String beschreibung, String titel,String id) {
		super();
		this.betrag = betrag;
		this.datum = datum;
		this.ersteller = ersteller;
		this.zielKonto = zielKonto;
		this.id = id;
		this.obsolet = false;
		this.beschreibung = beschreibung;
		this.titel = titel;
		ausgefuehrt = false;
	}

	public boolean isAusgefuehrt() {
		return ausgefuehrt;
	}

	public void setAusgefuehrt(boolean ausgefuehrt) {
		this.ausgefuehrt = ausgefuehrt;
	}
	public void setZielKonto(Konto zielKonto) {
		this.zielKonto = zielKonto;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public String getTitel() {
		return titel;
	}

	public String getId() {
		return id;
	}

	public boolean isObsolet() {
		return obsolet;
	}

	public void setObsolet(boolean obsolet) {
		this.obsolet = obsolet;
	}

	public double getBetrag() {
		return betrag;
	}

	public Date getDatum() {
		return datum;
	}

	public Nutzer getErsteller() {
		return ersteller;
	}

	public Konto getZielKonto() {
		return zielKonto;
	}

	@Override
	public String getSaveString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		return id+"";
	}

	@Override
	public DataSet getXMLDataSet() {
		DataSet ret = new TransaktionDataSet(betrag,datum,ersteller,zielKonto,beschreibung,titel);
		ret.addKey("id", id);
		ret.addKey("obsolet", obsolet);
		ret.addKey("ausgefuehrt", ausgefuehrt);
		return ret;
	}
		
}
