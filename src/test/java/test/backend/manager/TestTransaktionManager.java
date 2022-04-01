package test.backend.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.manager.TransaktionEntityManager;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.EntityManager;

public class TestTransaktionManager {
	@Test
	public void addAndGetTransaktion() {
		EntityManager testManager = new TransaktionEntityManager();
		// create mock Transaktions
		Transaktion mockTransaktion1 = mock(Transaktion.class);
		Transaktion mockTransaktion2 = mock(Transaktion.class);
		when(mockTransaktion1.getId()).thenReturn("1");
		when(mockTransaktion2.getId()).thenReturn("2");
		// save mock Transaktions
		testManager.save(mockTransaktion1);
		testManager.save(mockTransaktion2);
		// get Transaktions and chek
		assertEquals(testManager.get("1").get(), mockTransaktion1);
		assertEquals(testManager.get("2").get(), mockTransaktion2);
	}

	@Test
	public void addAndDeleteTransaktion() {
		EntityManager testManager = new TransaktionEntityManager();
		// create real Transaktion to check obsolete function
		Transaktion t1 = new Transaktion(new BigDecimal("323"), mock(Date.class), mock(Nutzer.class), mock(Konto.class), "Beschreibung", "titel",
				"1");
		// save mock Transaktions
		testManager.save(t1);
		// check that Transaktion is there
		assertTrue(testManager.get("1").isPresent());
		// delete Transaktion from manager
		testManager.delete("1");
		// check that Transaktion is obsolete
		assertTrue(((Transaktion) testManager.get("1").get()).isObsolet());
	}

	@Test
	public void addTransaktionAndGetAll() {
		EntityManager testManager = new TransaktionEntityManager();
		// create mock Transaktions
		Transaktion mockTransaktion1 = mock(Transaktion.class);
		Transaktion mockTransaktion2 = mock(Transaktion.class);
		when(mockTransaktion1.getId()).thenReturn("1");
		when(mockTransaktion2.getId()).thenReturn("2");
		// save mock Transaktions
		testManager.save(mockTransaktion1);
		testManager.save(mockTransaktion2);
		// get List with all Transaktions
		List<Transaktion> TransaktionList = testManager.getAll();
		assertTrue(TransaktionList.contains(mockTransaktion1));
		assertTrue(TransaktionList.contains(mockTransaktion2));

	}
}
