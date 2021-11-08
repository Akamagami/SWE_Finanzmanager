package constants;

import backend.nutzer.*;
import backend.manager.*;
import backend.konten.*;

public enum ClassType {
	NUTZER("Nutzer",Nutzer.class),
	KONTO("Konto",Konto.class);
	
	
	private String displayName;
	private Class<?> cls;
	
	ClassType(String displayName,Class<?> cls) {
		this.displayName = displayName;
		this.cls = cls;
	}
	public String getDisplayName() {
		return displayName;
	}
	public Class<?> getMgr() {
		return cls;
	}
}
