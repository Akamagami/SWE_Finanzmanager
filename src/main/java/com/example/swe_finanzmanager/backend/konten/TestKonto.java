package com.example.swe_finanzmanager.backend.konten;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.nutzer.Nutzer;

public class TestKonto {
	@Test
	public void testAddingMitglieder() {
		//erstellung Nutzer
		Nutzer testNutzer1 = mock(Nutzer.class);
		Nutzer testNutzer2 = mock(Nutzer.class);
		Nutzer testNutzer3 = mock(Nutzer.class);
		Nutzer testNutzer4 = mock(Nutzer.class);

		//using normal Konstruktor instead of factory
		Konto testKonto = new Konto(200, testNutzer1, "Name", "Beschreibung", 42, "9238472");
		//adding ersteller again to make sure count does not change
		testKonto.addMitglied(testNutzer1);
		assertEquals(testKonto.getMitgliedList().size(),1);
		//add 2 new Users to make sur count does change
		testKonto.addMitglied(testNutzer2);
		testKonto.addMitglied(testNutzer3);
		assertTrue(testKonto.nutzerIstMitglied(testNutzer1));		
		assertTrue(testKonto.nutzerIstMitglied(testNutzer2));
		assertTrue(testKonto.nutzerIstMitglied(testNutzer3));
		assertEquals(testKonto.getMitgliedList().size(),3);
		//add 4 and 3 to make sure dupliates dont increse count aswell
		testKonto.addMitglied(testNutzer4);
		testKonto.addMitglied(testNutzer3);
		assertTrue(testKonto.nutzerIstMitglied(testNutzer4));
		assertTrue(testKonto.nutzerIstMitglied(testNutzer3));
		assertEquals(testKonto.getMitgliedList().size(),4);
		//remove 3 and 4
		assertTrue(testKonto.removeMitglied(testNutzer4));
		assertTrue(testKonto.removeMitglied(testNutzer3));
		assertFalse(testKonto.nutzerIstMitglied(testNutzer4));
		assertFalse(testKonto.nutzerIstMitglied(testNutzer3));
		assertEquals(testKonto.getMitgliedList().size(),2);
		//remove 1 -> shouldnt work da mann den ersteller nicht ändern kann
		assertFalse(testKonto.removeMitglied(testNutzer1));
		assertTrue(testKonto.nutzerIstMitglied(testNutzer1));	
	}
	@Test
	public void testKontostandRechnung() {
		double initKontostand = 3209;
		double t1Value = 33;
		double t2Value = 44;
		double t3Value = 55;
		
		Konto testKonto = new Konto(initKontostand, mock(Nutzer.class), "Name", "Beschreibung", 42, "9238472");
		//n paar transaktionen
		
		Transaktion t1 = mock(Transaktion.class);
		Transaktion t2 = mock(Transaktion.class);
		Transaktion t3 = mock(Transaktion.class);//obsolet(aslo kein betrag nötig
		
		when(t1.getBetrag()).thenReturn(t1Value);
		when(t2.getBetrag()).thenReturn(t2Value);
		when(t3.getBetrag()).thenReturn(t3Value);
		when(t1.isObsolet()).thenReturn(false);
		when(t2.isObsolet()).thenReturn(false);
		when(t3.isObsolet()).thenReturn(true);
		//add transaktionen
		testKonto.addTransaktion(t1);
		testKonto.addTransaktion(t2);
		testKonto.addTransaktion(t3);
		//check kontostand value
		assertEquals(testKonto.getKontostand(),initKontostand + t1Value + t2Value);
		assertEquals(testKonto.getInitKontostand(),initKontostand);
		
	}
}
