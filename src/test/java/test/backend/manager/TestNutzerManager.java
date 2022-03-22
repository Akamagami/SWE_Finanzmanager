package test.backend.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.manager.NutzerEntityManager;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.manager.NutzerEntityManager;
import com.example.swe_finanzmanager.backend.speicher.EntityManager;

public class TestNutzerManager {
	@Test
	public void addAndGetNutzer() {
		EntityManager testManager = new NutzerEntityManager();
		// create mock Nutzers
		Nutzer mockNutzer1 = mock(Nutzer.class);
		Nutzer mockNutzer2 = mock(Nutzer.class);
		when(mockNutzer1.getId()).thenReturn("1");
		when(mockNutzer2.getId()).thenReturn("2");
		// save mock Nutzers
		testManager.save(mockNutzer1);
		testManager.save(mockNutzer2);
		// get Nutzers and chek
		assertEquals(testManager.get("1").get(), mockNutzer1);
		assertEquals(testManager.get("2").get(), mockNutzer2);
	}

	@Test
	public void addAndDeleteNutzer() {
		EntityManager testManager = new NutzerEntityManager();
		// create mock Nutzers
		Nutzer mockNutzer1 = mock(Nutzer.class);
		when(mockNutzer1.getId()).thenReturn("1");
		// save mock Nutzers
		testManager.save(mockNutzer1);
		// check that Nutzer is there
		assertTrue(testManager.get("1").isPresent());
		// delete Nutzer from manager
		testManager.delete("1");
		// check that Nutzer is gone
		assertTrue(testManager.get("1").isEmpty());
	}

	@Test
	public void addNutzerAndGetAll() {
		EntityManager testManager = new NutzerEntityManager();
		// create mock Nutzers
		Nutzer mockNutzer1 = mock(Nutzer.class);
		Nutzer mockNutzer2 = mock(Nutzer.class);
		when(mockNutzer1.getId()).thenReturn("1");
		when(mockNutzer2.getId()).thenReturn("2");
		// save mock Nutzers
		testManager.save(mockNutzer1);
		testManager.save(mockNutzer2);
		// get List with all Nutzers
		List<Nutzer> NutzerList = testManager.getAll();
		assertTrue(NutzerList.contains(mockNutzer1));
		assertTrue(NutzerList.contains(mockNutzer2));
	}
}
