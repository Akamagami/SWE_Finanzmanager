package com.example.swe_finanzmanager.backend.speicher;

public abstract class SavableObject {
	
	private String id;
	
	public SavableObject(String id) {
		this.id = id;
	}
	public String getSaveString()
	{
		return "no Save String";
	}
	public String getId() {
		return id;
	}
	public DataSet getXMLDataSet()
	{
		return null;
	}
}
