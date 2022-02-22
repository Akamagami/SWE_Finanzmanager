package execution;

import java.sql.Date;
import java.util.List;

import backend.dataSets.KontoDataSet;
import backend.dataSets.NutzerDataSet;
import backend.dataSets.TransaktionDataSet;
import backend.konten.Konto;
import backend.nutzer.Nutzer;
import backend.persistence.XMLAdapter;
import backend.speicher.DataSet;
import backend.speicher.Speicher;
import constants.ClassType;

public class Main_Backend {

	public static void main(String[] args) {
		Speicher sp = new Speicher();
		sp.setDataAdapter(new XMLAdapter());
		
		DataSet N1 = new NutzerDataSet("Hi", "Work", 2);
		
		Nutzer n1 = (Nutzer) sp.createObject(N1);
		System.out.println("Nutzer:" + n1.toString());
		
		n1 = (Nutzer) sp.createObject(N1);
		
		
		
		DataSet N2 = new NutzerDataSet("Not", "Cool", 4);
		n1 = (Nutzer) sp.createObject(N2);
		
		for(Nutzer n:(List<Nutzer>)(List<?>) sp.getAll(ClassType.NUTZER)) {
			System.out.println("nnutzer:" + n.toString());
		}
		/*------------------------------------------------------------------------*/
		
		DataSet K1 = new KontoDataSet(100.23,(Nutzer) sp.getObject(ClassType.NUTZER, "2"),"Konto1","Beeee",2);
		Konto ko1 = (Konto) sp.createObject(K1);
		ko1.addMitglied((Nutzer) sp.getObject(ClassType.NUTZER, "1"));
		ko1.addMitglied(n1);
		for(Konto n:(List<Konto>)(List<?>) sp.getAll(ClassType.KONTO)) {
			System.out.println("Konto:" + n.toString());
		}
		
		TransaktionDataSet t1 = new TransaktionDataSet(100.0, Date.valueOf("2020-11-10"), n1, ko1, "Schutzgeld", "Merci");
		
		sp.createAndAddTransaktion(t1);
		System.out.println("Before:" + ko1.getKontostand());
		sp.updateTrVw(Date.valueOf("2020-11-11"));
		System.out.println("After:" + ko1.getKontostand());
		
		
		sp.save();
	}

}
