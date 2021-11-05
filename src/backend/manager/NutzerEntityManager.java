package backend.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.nutzer.Nutzer;
import backend.speicher.EntityManager;




public class NutzerEntityManager implements EntityManager<Nutzer> {

	private HashMap<String,Nutzer> list = new HashMap<String,Nutzer>();
	
	@Override
	public Optional<Nutzer> get(String id) {
		return Optional.of(list.get(id));
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
