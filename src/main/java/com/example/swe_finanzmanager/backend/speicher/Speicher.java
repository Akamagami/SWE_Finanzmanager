package com.example.swe_finanzmanager.backend.speicher;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.swe_finanzmanager.backend.dataSets.TransaktionDataSet;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.konten.TransaktionsVerwaltung;
import com.example.swe_finanzmanager.backend.persistence.DataAdapter;
import com.example.swe_finanzmanager.backend.speicher.manager.EntityManagerList;
import com.example.swe_finanzmanager.backend.speicher.manager.FactoryList;
import com.example.swe_finanzmanager.constants.ClassType;

public class Speicher {
	
	EntityManagerList emList = new EntityManagerList();
	FactoryList facList = new FactoryList();
	TransaktionsVerwaltung trVw = new TransaktionsVerwaltung(Date.valueOf("2020-10-10"));
	DataAdapter dataAdapter = null;
	
	public TransaktionsVerwaltung getTrVw() {
		return trVw;
	}

	public Speicher() {
		super();
		init();
	}
	
	private void init() {
		
		
	}
	/*--------------------------------------------------------------------------------------------------------------*/
	public void save(ClassType type, Object o) {//Funktion um ein Objekt einer Klasse im Entity Manager zu speichern, wird typischerweise immer direkt von der Create methode aufgerufen
		emList.get(type).save(o);
	}

	public void update(ClassType type, String id, Object[] params) {//Funktion um die update methode eiunes Objekts aufzurufen
		emList.get(type).update(id, params);
	}

	public Object getObject(ClassType type, String id) {//holt ein objekt je nach class type und id
		Optional ret = emList.get(type).get(id);
		if(ret.isPresent()) {
			return ret.get();
		} else {
			return null;
		}
		
	}

	public List<Object> getAll(ClassType type) {//gibt alle object eines class types zur�ck
		return emList.get(type).getAll();
	}

	public void delete(ClassType type, String id) {//l�scht ein Objekt aus dem jeiligen entity manager
		emList.get(type).delete(id);
	}

	/*--------------------------------------------------------------------------------------------------------------*/
	private Object createObject(DataSet dataSet, Optional<String> optId) {//interne create methode, wird entweder mit oder ohne id aufgerufen
		
		Object ret = facList.get(dataSet.getClassType()).create(dataSet, optId);
		this.save(dataSet.getClassType(), ret);
		return ret;
	}

	/*--------------------------------------------------------------------------------------------------------------*/
	public Object createObject(DataSet dataSet) {//Diese Methode erstellt ein Objekt ohne eine Id festulegen, die ID wird automatisch generiert
		if(dataSet.hasKey("id")) {
			return this.createObject(dataSet, (String) dataSet.get("id"));
		} else {
			return this.createObject(dataSet, Optional.empty());
		}
		
		
	}

	private Object createObject(DataSet dataSet, String id) {//Diese Methode erstellt ein Ojekt mit einer festgelegten id, kann nur vom speicher selbst aufgerufen werden
		return this.createObject(dataSet, Optional.of(id));
	}
	/*--------------------------------------------------------------------------------------------------------------*/
	
	public void setDataAdapter (DataAdapter dA) {
		dataAdapter = dA;
	}
	
	public void save() {
		dataAdapter.writeAndSave(this);
	}
	public void load() {
		dataAdapter.readAndLoad(this);
	}
	/*--------------------------------------------------------------------------------------------------------------*/
	private Transaktion createAndAddTransaktion(TransaktionDataSet dataSet,Optional<String> optId) {
		Transaktion ret = (Transaktion) this.createObject(dataSet,optId);
		trVw.addTransaktion(ret);
		//trVw.update();
		return ret;
	}
	public Transaktion createAndAddTransaktion(TransaktionDataSet dataSet) {
		if(dataSet.hasKey("id")) {
			return this.createAndAddTransaktion(dataSet, Optional.of((String) dataSet.get("id")));
		} else {
			return this.createAndAddTransaktion(dataSet, Optional.empty());
		}
	}
	
	
	public void updateTrVw(Date datum) {
		trVw.update(datum);
	}

/*--------------------------------------------------------------------------------------------------------------*/
	
	
}
