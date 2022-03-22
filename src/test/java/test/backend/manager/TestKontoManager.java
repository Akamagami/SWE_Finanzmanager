package test.backend.manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.manager.KontoEntityManager;
import com.example.swe_finanzmanager.backend.speicher.EntityManager;

public class TestKontoManager {
	@Test
	public void addAndGetKonto() {
		EntityManager testManager = new KontoEntityManager();
		// create mock kontos
		Konto mockKonto1 = mock(Konto.class);
		Konto mockKonto2 = mock(Konto.class);
		when(mockKonto1.getId()).thenReturn("1");
		when(mockKonto2.getId()).thenReturn("2");
		// save mock kontos
		testManager.save(mockKonto1);
		testManager.save(mockKonto2);
		// get kontos and chek
		assertEquals(testManager.get("1").get(), mockKonto1);
		assertEquals(testManager.get("2").get(), mockKonto2);
	}

	@Test
	public void addAndDeleteKonto() {
		EntityManager testManager = new KontoEntityManager();
		// create mock kontos
		Konto mockKonto1 = mock(Konto.class);
		when(mockKonto1.getId()).thenReturn("1");
		// save mock kontos
		testManager.save(mockKonto1);
		// check that konto is there
		assertTrue(testManager.get("1").isPresent());
		// delete Konto from manager
		testManager.delete("1");
		// check that konto is gone
		assertTrue(testManager.get("1").isEmpty());
	}

	@Test
	public void addKontoAndGetAll() {
		EntityManager testManager = new KontoEntityManager();
		// create mock kontos
		Konto mockKonto1 = mock(Konto.class);
		Konto mockKonto2 = mock(Konto.class);
		when(mockKonto1.getId()).thenReturn("1");
		when(mockKonto2.getId()).thenReturn("2");
		// save mock kontos
		testManager.save(mockKonto1);
		testManager.save(mockKonto2);
		// get List with all Kontos
		List<Konto> kontoList = testManager.getAll();
		assertTrue(kontoList.contains(mockKonto1));
		assertTrue(kontoList.contains(mockKonto2));

	}
}
