package backend.speicher.manager;

import java.util.HashMap;

import backend.factories.KontoFactory;
import backend.manager.KontoEntityManager;
import backend.manager.NutzerEntityManager;
import backend.speicher.EntityManager;
import constants.ClassType;

public class EntityManagerList {
	
	private HashMap<ClassType,EntityManager> managers = new HashMap<ClassType,EntityManager>();
	
	public EntityManagerList() {
		init();
	}
	
	private void init() {		
			managers.put(ClassType.NUTZER,new NutzerEntityManager());
			managers.put(ClassType.KONTO, new KontoEntityManager());
	}
	public EntityManager get(ClassType cType) {
		return managers.get(cType);
	}
	
	
}
