package backend.speicher.manager;

import java.util.HashMap;

import backend.factories.KontoFactory;
import backend.factories.NutzerFactory;
import backend.speicher.ElementFactory;
import constants.ClassType;

public class FactoryList {
	
	private HashMap<ClassType,ElementFactory> factories = new HashMap<ClassType,ElementFactory>();
	
	public FactoryList() {
		init();
	}
	
	private void init() {		
			factories.put(ClassType.NUTZER,new NutzerFactory());
			factories.put(ClassType.KONTO, new KontoFactory());
	}
	public ElementFactory get(ClassType cType) {
		return factories.get(cType);
	}
	
	
}
