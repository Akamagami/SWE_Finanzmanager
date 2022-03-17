package test.backend.konto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.konten.TransaktionsVerwaltung;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;

public class TestTransaktionverwaltung {
	@Test
	public void testAddingPastTransaktion() {
		Date present = Date.valueOf("2020-11-11");
		Date past = Date.valueOf("2020-10-11");
		
		double t1Value = 32;
		//echtes Konto, das verknüpfung wichtig ist
		Konto testKonto = new Konto(200, mock(Nutzer.class), "Name", "Beschreibung", 42, "9238472");
		
		Transaktion t1 = new Transaktion (t1Value, past ,mock(Nutzer.class),testKonto,"Beschreibung","titel","834329");
		
		TransaktionsVerwaltung tvw = new TransaktionsVerwaltung(present);
		
		tvw.addTransaktion(t1);
		//nun sollte t1 ausgefürt sein, und der Konostand von TestKonto um t1Value erhöht
		assertTrue(t1.isAusgefuehrt());
		assertEquals(testKonto.getKontostand(),testKonto.getInitKontostand()+t1.getBetrag());
		//erneutes hinzufügen der Transaktion ändert nichts
		tvw.addTransaktion(t1);
		assertEquals(testKonto.getKontostand(),testKonto.getInitKontostand()+t1.getBetrag());
		//transaktion tu obsolet, Kontostand sollte = init Kontostand sein
		t1.setObsolet(true);
		assertEquals(testKonto.getKontostand(),testKonto.getInitKontostand());
	}
	@Test
	public void testAddingFutureTransaktion() {
		Date present = Date.valueOf("2020-11-11");
		Date future = Date.valueOf("2020-12-11");
		
		double t1Value = 32;
		//echtes Konto, das verknüpfung wichtig ist
		Konto testKonto = new Konto(200, mock(Nutzer.class), "Name", "Beschreibung", 42, "9238472");
		
		Transaktion t1 = new Transaktion (t1Value, future ,mock(Nutzer.class),testKonto,"Beschreibung","titel","834329");
		
		TransaktionsVerwaltung tvw = new TransaktionsVerwaltung(present);

		//hinzufügen der transaktion, sie sollte nicht ausgeführt sein und der Wert des Kontos nicht verändert
		tvw.addTransaktion(t1);
		assertFalse(t1.isAusgefuehrt());
		assertEquals(testKonto.getKontostand(),testKonto.getInitKontostand());
		//ändern der Zeit, nun sollte die Transaktion ausgeführt sein
		tvw.update(future);
		assertTrue(t1.isAusgefuehrt());
		assertEquals(testKonto.getKontostand(),testKonto.getInitKontostand()+t1.getBetrag());
		
	}

}
