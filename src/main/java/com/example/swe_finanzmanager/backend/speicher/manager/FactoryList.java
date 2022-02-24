package com.example.swe_finanzmanager.backend.speicher.manager;

import java.util.HashMap;

import com.example.swe_finanzmanager.backend.factories.KontoFactory;
import com.example.swe_finanzmanager.backend.factories.NutzerFactory;
import com.example.swe_finanzmanager.backend.factories.TransaktionFactory;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;
import com.example.swe_finanzmanager.constants.ClassType;



public class FactoryList {
	
	private HashMap<ClassType,ElementFactory> factories = new HashMap<ClassType,ElementFactory>();
	
	public FactoryList() {
		init();
	}
	
	private void init() {		
			factories.put(ClassType.NUTZER,new NutzerFactory());
			factories.put(ClassType.KONTO, new KontoFactory());
			factories.put(ClassType.TRANSAKTION, new TransaktionFactory());
	}
	public ElementFactory get(ClassType cType) {
		return factories.get(cType);
	}
	
	
}
