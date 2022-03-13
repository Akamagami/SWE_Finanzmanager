package com.example.swe_finanzmanager.backend.speicher;


import java.util.Optional;


public interface ElementFactory {
	
	public String getIndex();
	
	public void setIndex(String id);
	
	public Object create(DataSet dataSet,Optional<String> optId);
		
}
