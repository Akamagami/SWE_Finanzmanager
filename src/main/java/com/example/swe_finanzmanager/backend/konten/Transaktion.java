package com.example.swe_finanzmanager.backend.konten;

import java.math.BigDecimal;
import java.sql.Date;

import com.example.swe_finanzmanager.backend.dataSets.TransaktionDataSet;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.SavableObject;

public class Transaktion extends SavableObject {

	private BigDecimal betrag;
	private Date datum;
	private boolean obsolet;
	private String beschreibung;
	private String titel;
	private boolean ausgefuehrt;
	
	private Nutzer ersteller;
	private Konto zielKonto;
	
	public Transaktion(BigDecimal betrag, Date datum, Nutzer ersteller, Konto zielKonto,String beschreibung, String titel,String id) {
		super(id);
		this.betrag = betrag;
		this.datum = datum;
		this.ersteller = ersteller;
		this.zielKonto = zielKonto;
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
	public boolean isObsolet() {
		return obsolet;
	}

	public void setObsolet(boolean obsolet) {
		this.obsolet = obsolet;
	}

	public BigDecimal getBetrag() {
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
	public String toString() {
		return this.getId()+"";
	}

	@Override
	public DataSet getXMLDataSet() {
		DataSet ret = new TransaktionDataSet(betrag,datum,ersteller,zielKonto,beschreibung,titel);
		ret.addKey("id", this.getId());
		ret.addKey("obsolet", obsolet);
		ret.addKey("ausgefuehrt", ausgefuehrt);
		return ret;
	}
		
}
