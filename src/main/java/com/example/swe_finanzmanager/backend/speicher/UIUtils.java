package com.example.swe_finanzmanager.backend.speicher;

import com.example.swe_finanzmanager.backend.konten.Transaktion;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.swe_finanzmanager.backend.dataSets.KontoDataSet;
import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.dataSets.TransaktionDataSet;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.persistence.XMLAdapter;
import com.example.swe_finanzmanager.constants.ClassType;

/*Verwendung

 * UIUtils uiU = new UIUtils(sp)
 * 
 * uiU.getAllNutzer();
 * usw
 */


public class UIUtils {

	Speicher sp;
	
	public UIUtils() {
		initSpeicher();
	}
	
	private void initSpeicher() {
		sp = new Speicher();
		sp.setDataAdapter(new XMLAdapter());
		this.load();
		sp.updateTrVw(Date.valueOf(LocalDate.now()));	
	}
	
	
	public Nutzer getNutzer(String id) {
		return (Nutzer) sp.getObject(ClassType.NUTZER, id);
	}
	public Transaktion getTransaktion(String id) {
		return (Transaktion) sp.getObject(ClassType.TRANSAKTION, id);
	}
	public Konto Konto(String id) {
		return (Konto) sp.getObject(ClassType.KONTO, id);
	}
	
	public List<Nutzer> getAllNutzer(){
		return (List<Nutzer>)(List<?>) sp.getAll(ClassType.NUTZER);
	}
	public List<Transaktion> getAllTransaktionen(){
		return (List<Transaktion>)(List<?>) sp.getAll(ClassType.TRANSAKTION);
	}
	public List<Konto> getAllKonten(){
		return (List<Konto>)(List<?>) sp.getAll(ClassType.KONTO);
	}
	
	public List<Konto> getNutzerKonten(Nutzer n){
		List<Konto> ret = new ArrayList<Konto>();
		
		for(Konto k:this.getAllKonten()) {
			if(k.nutzerIstMitglied(n)) {
				ret.add(k);
			}
		}
		return ret;
	}
	
	public void load() {
		sp.load();
	}
	public void save() {
		sp.save();
	}
	
	public Nutzer createNutzer(String vorname, String nachname, int icon) {
		NutzerDataSet newObj = new NutzerDataSet(vorname, nachname, icon);
		return (Nutzer) sp.createObject(newObj);
	}
	public Konto createKonto(BigDecimal kontostand, Nutzer ersteller, String name, String beschreibung, int icon) {
		KontoDataSet newObj = new KontoDataSet(kontostand, ersteller, name, beschreibung, icon);
		return (com.example.swe_finanzmanager.backend.konten.Konto) sp.createObject(newObj);
	}
	public Transaktion createTransaktion(BigDecimal betrag, Date datum, Nutzer ersteller, Konto zielKonto, String beschreibung, String titel) {
		TransaktionDataSet newObj = new TransaktionDataSet(betrag, datum, ersteller, zielKonto, beschreibung, titel);
		return (Transaktion) sp.createObject(newObj);
	}
	
	
}
