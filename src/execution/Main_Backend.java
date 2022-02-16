package execution;

import java.util.List;

import backend.dataSets.KontoDataSet;
import backend.dataSets.NutzerDataSet;
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
		System.out.println("Nutzer:" + n1.toString());
		
		
		
		for(Nutzer n:(List<Nutzer>)(List<?>) sp.getAll(ClassType.NUTZER)) {
			System.out.println("nnutzer:" + n.toString());
		}
		/*------------------------------------------------------------------------*/
		
		DataSet K1 = new KontoDataSet(100.23,(Nutzer) sp.getObject(ClassType.NUTZER, "2"),"Konto1","Beeee",2);
		Konto ko1 = (Konto) sp.createObject(K1);
		ko1.addMitglied((Nutzer) sp.getObject(ClassType.NUTZER, "1"));
		for(Konto n:(List<Konto>)(List<?>) sp.getAll(ClassType.KONTO)) {
			System.out.println("Konto:" + n.toString());
		}
		
		sp.save();
	}

}
