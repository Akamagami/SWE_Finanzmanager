package backend.speicher;

import java.util.HashMap;

import constants.ClassType;

public abstract class DataSet {
	
	private HashMap<String,Object> values = new HashMap<String,Object>();
	
	private ClassType classType= null;

	public HashMap<String, Object> getValues() {
		return values;
	}
	
	public DataSet(ClassType t) {
		classType = t;
	}
}
