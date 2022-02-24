package com.example.swe_finanzmanager.backend.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.speicher.EntityManager;

public class TransaktionEntityManager implements EntityManager<Transaktion> {

private HashMap<String,Transaktion> list = new HashMap<String,Transaktion>();
	
	@Override
	public Optional<Transaktion> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Transaktion e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Transaktion> getAll() {
		List<Transaktion> ret = new ArrayList<Transaktion>();
			for(Map.Entry<String, Transaktion> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		// nothing		
	}
}
