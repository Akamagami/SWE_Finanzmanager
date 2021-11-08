package backend.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.konten.Konto;
import backend.nutzer.Nutzer;
import backend.speicher.EntityManager;

public class KontoEntityManager implements EntityManager<Konto> {
	
	private HashMap<String,Konto> list = new HashMap<String,Konto>();
	
	@Override
	public Optional<Konto> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Konto e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Konto> getAll() {
		List<Konto> ret = new ArrayList<Konto>();
			for(Map.Entry<String, Konto> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		// nothing		
	}

}
