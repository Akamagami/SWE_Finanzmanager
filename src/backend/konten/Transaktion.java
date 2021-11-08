package backend.konten;

import backend.nutzer.Nutzer;

public class Transaktion {

	private double betrag;
	private double datum;
	private boolean obsolet;
	private String id;
	
	private Nutzer ersteller;
	private Konto ZielKonto;
	
	public Transaktion(double betrag, double datum, Nutzer ersteller, Konto zielKonto,String id) {
		super();
		this.betrag = betrag;
		this.datum = datum;
		this.ersteller = ersteller;
		ZielKonto = zielKonto;
		this.id = id;
		this.obsolet = false;
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

	public double getDatum() {
		return datum;
	}

	public Nutzer getErsteller() {
		return ersteller;
	}

	public Konto getZielKonto() {
		return ZielKonto;
	}
		
}
