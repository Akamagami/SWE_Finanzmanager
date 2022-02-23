package com.example.swe_finanzmanager.backend.speicher;


import com.example.swe_finanzmanager.constants.ClassType;

import java.util.HashMap;

public abstract class DataSet {
	
	private HashMap<String,Object> values = new HashMap<String,Object>();
	
	private ClassType classType= null;

	public HashMap<String, Object> getValues() {
		return values;
	}
	
	public DataSet(ClassType t) {
		classType = t;
	}
	
	public Object get(String key) {
		return values.get(key);
	}
	public ClassType getClassType() {
		return classType;
	}
	protected void addKey(String key, Object value) {
		values.put(key, value);
	}
}
