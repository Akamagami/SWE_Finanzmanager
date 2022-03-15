package com.example.swe_finanzmanager.backend.manager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.EntityManager;



public class NutzerEntityManager implements EntityManager<Nutzer> {

	private HashMap<String,Nutzer> list = new HashMap<String,Nutzer>();
	
	@Override
	public Optional<Nutzer> get(String id) {
		if(list.containsKey(id)) {
			return Optional.of(list.get(id));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Nutzer e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Nutzer> getAll() {
		List<Nutzer> ret = new ArrayList<Nutzer>();
			for(Map.Entry<String, Nutzer> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		// nothing		
	}

}
