package execution;

import java.util.List;


import backend.dataSets.NutzerDataSet;
import backend.nutzer.Nutzer;
import backend.speicher.DataSet;
import backend.speicher.Speicher;
import constants.ClassType;

public class Main_Backend {

	public static void main(String[] args) {
		Speicher sp = new Speicher();
		
		DataSet N1 = new NutzerDataSet("Hi", "Work", 2);
		
		Nutzer n1 = (Nutzer) sp.createObject(N1);
		System.out.println("Nutzer:" + n1.toString());
		
		n1 = (Nutzer) sp.createObject(N1);
		System.out.println("Nutzer:" + n1.toString());
		
		sp.delete(ClassType.NUTZER, "1");
		
		for(Nutzer n:(List<Nutzer>)(List<?>) sp.getAll(ClassType.NUTZER)) {
			System.out.println("nnutzer:" + n.toString());
		}
	}

}
