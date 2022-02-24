package com.example.swe_finanzmanager.backend.execution;

import java.sql.Date;
import java.util.List;

import com.example.swe_finanzmanager.backend.dataSets.KontoDataSet;
import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.dataSets.TransaktionDataSet;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.persistence.XMLAdapter;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.Speicher;
import com.example.swe_finanzmanager.constants.ClassType;

public class execTest {

	public static void main(String[] args) {
		Speicher sp = new Speicher();
		sp.setDataAdapter(new XMLAdapter());
		
		sp.load();
		
		/*DataSet N1 = new NutzerDataSet("Hi", "Work", 2);
		
		Nutzer n1 = (Nutzer) sp.createObject(N1);
		System.out.println("Nutzer:" + n1.toString());
		
		n1 = (Nutzer) sp.createObject(N1);
		
		
		
		DataSet N2 = new NutzerDataSet("Not", "Cool", 4);
		n1 = (Nutzer) sp.createObject(N2);*/
		
		for(Nutzer n:(List<Nutzer>)(List<?>) sp.getAll(ClassType.NUTZER)) {
			System.out.println("nutzer:" + n.getName().fullName());
		}
		/*------------------------------------------------------------------------*/
		
		/*DataSet K1 = new KontoDataSet(100.23,(Nutzer) sp.getObject(ClassType.NUTZER, "2"),"Konto1","Beeee",2);
		Konto ko1 = (Konto) sp.createObject(K1);
		ko1.addMitglied((Nutzer) sp.getObject(ClassType.NUTZER, "1"));
		ko1.addMitglied(n1);*/
		for(Konto n:(List<Konto>)(List<?>) sp.getAll(ClassType.KONTO)) {
			System.out.println("Konto:" + n.toString());
		}
		
		/*TransaktionDataSet t1 = new TransaktionDataSet(100.0, Date.valueOf("2020-11-10"), n1, ko1, "Schutzgeld", "Merci");
		
		sp.createAndAddTransaktion(t1);
		System.out.println("Before:" + ko1.getKontostand());
		sp.updateTrVw(Date.valueOf("2020-11-11"));*/
		Konto ko1 = (Konto) sp.getObject(ClassType.KONTO, "1");
		System.out.println("After:" + ko1.getKontostand());
		
		
		
	 sp.save();
	}

}