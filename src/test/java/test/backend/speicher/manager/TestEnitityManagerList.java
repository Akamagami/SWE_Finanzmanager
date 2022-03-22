package test.backend.speicher.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.speicher.EntityManager;
import com.example.swe_finanzmanager.backend.speicher.manager.EntityManagerList;
import com.example.swe_finanzmanager.constants.ClassType;

public class TestEnitityManagerList {
	@Test
	public void getAllClassTypeEntityManager() {
		EntityManagerList testEmList = new EntityManagerList();
		for (ClassType t : ClassType.values()) {
			assertTrue(testEmList.get(t) instanceof EntityManager);
		}
	}
}
