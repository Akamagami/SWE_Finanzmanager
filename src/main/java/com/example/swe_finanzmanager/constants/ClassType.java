package com.example.swe_finanzmanager.constants;

import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;

public enum ClassType {
	NUTZER("Nutzer",Nutzer.class),
	KONTO("Konto",Konto.class),
	TRANSAKTION("Transaktion",Transaktion.class);
	
	
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
