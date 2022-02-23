package com.example.swe_finanzmanager.backend.speicher.manager;


import com.example.swe_finanzmanager.backend.manager.KontoEntityManager;
import com.example.swe_finanzmanager.backend.manager.NutzerEntityManager;
import com.example.swe_finanzmanager.backend.manager.TransaktionEntityManager;
import com.example.swe_finanzmanager.backend.speicher.EntityManager;
import com.example.swe_finanzmanager.constants.ClassType;

import java.util.HashMap;

public class EntityManagerList {
	
	private HashMap<ClassType, EntityManager> managers = new HashMap<ClassType,EntityManager>();
	
	public EntityManagerList() {
		init();
	}
	
	private void init() {		
			managers.put(ClassType.NUTZER,new NutzerEntityManager());
			managers.put(ClassType.KONTO, new KontoEntityManager());
			managers.put(ClassType.TRANSAKTION, new TransaktionEntityManager());
	}
	public EntityManager get(ClassType cType) {
		return managers.get(cType);
	}
	
	
}
