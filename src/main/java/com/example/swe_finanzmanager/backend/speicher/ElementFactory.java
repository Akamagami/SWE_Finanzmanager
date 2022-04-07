package com.example.swe_finanzmanager.backend.speicher;


import java.util.Optional;


public abstract class ElementFactory {
	
	private int index = 1;
	
	public String getIndex() {
		return index + "";
	}
	
	public void setIndex(String id) {
		index = Integer.parseInt(id);

	}
	
	public Object create(DataSet dataSet,Optional<String> optId){
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
			if(Integer.parseInt(newIndex)+1 > index) {
				index = Integer.parseInt(newIndex)+1;
			}
		} else {
			newIndex = index+"";
			index++;
		}
		return createObjectWithIndex(dataSet, newIndex);
	}
	
	protected Object createObjectWithIndex(DataSet dataSet, String index) {
		return null;
	}
}
