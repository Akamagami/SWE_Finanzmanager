package test.backend.speicher.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.speicher.ElementFactory;
import com.example.swe_finanzmanager.backend.speicher.manager.FactoryList;
import com.example.swe_finanzmanager.constants.ClassType;

public class TestFactoryList {
	@Test
	public void getAllClassTypeEntityManager() {
		FactoryList testFactoryList = new FactoryList();
		for (ClassType t : ClassType.values()) {
			assertTrue(testFactoryList.get(t) instanceof ElementFactory);
		}
	}
}
