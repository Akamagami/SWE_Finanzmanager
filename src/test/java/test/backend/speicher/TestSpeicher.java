package test.backend.speicher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.dataSets.TransaktionDataSet;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.Speicher;
import com.example.swe_finanzmanager.constants.ClassType;

public class TestSpeicher {
	/*
	 * da die Factories schon individuell getestet wurden wird hier nur der Nutzer
	 * Besipielhaft getestet
	 */

	@Test
	public void testDataCreationForNutzer() {
		// speicher Instanz
		Speicher sp = new Speicher();
		// Object Values
		String vorname = "Heinrich";
		String nachname = "Hunt";
		int icon = 42;
		int objectCount = 10;
		// mock the data set
		DataSet mockDataSet = createMockNutzerDataSet(vorname, nachname, icon);
		// create Object

		for (int i = 1; i <= objectCount; i++) {
			when(mockDataSet.get("icon")).thenReturn(icon + i);
			sp.createObject(mockDataSet);
		}
		// check count
		assertEquals(sp.getAll(ClassType.NUTZER).size(), objectCount);
		// check all object values to be the same
		for (int i = 1; i <= objectCount; i++) {
			Nutzer testNutzer = (Nutzer) sp.getObject(ClassType.NUTZER, i + "");

			assertEquals(testNutzer.getName().getVorname(), vorname);
			assertEquals(testNutzer.getName().getNachname(), nachname);
			assertEquals(testNutzer.getIcon(), icon + i);
		}
	}

	@Test
	public void createNutzerWithId() {
		// speicher Instanz
		Speicher sp = new Speicher();
		// Object Values
		String vorname = "Heinrich";
		String nachname = "Hunt";
		int icon = 42;

		int id = 44;
		// mock the data set
		DataSet mockDataSet = createMockNutzerDataSet(vorname, nachname, icon);
		when(mockDataSet.get("id")).thenReturn(id + "");
		when(mockDataSet.hasKey("id")).thenReturn(true);
		// create the object
		sp.createObject(mockDataSet);
		// id one should be a null pointer
		assertEquals(sp.getObject(ClassType.NUTZER, "1"), null);
		// check the object with the correct id
		Nutzer testNutzer = (Nutzer) sp.getObject(ClassType.NUTZER, id + "");
		assertEquals(testNutzer.getName().getVorname(), vorname);
		assertEquals(testNutzer.getName().getNachname(), nachname);
		assertEquals(testNutzer.getIcon(), icon);
		// delete Object wit id
		sp.delete(ClassType.NUTZER, id + "");
		assertEquals(sp.getObject(ClassType.NUTZER, id + ""), null);
	}

	/*
	 * Check special methods to add transaktion, since they neec to be added to the
	 * Transaktionsverwaltung aswell
	 */
	@Test
	public void testAddingTransaktion() {
		// speicher instanz
		Speicher sp = new Speicher();
		// data
		BigDecimal betrag = new BigDecimal("123");
		Date datum = Date.valueOf("1990-04-04"); // past transaktion
		Nutzer ersteller = mock(Nutzer.class);
		Konto zielKonto = new Konto(new BigDecimal("200"), mock(Nutzer.class), "Name", "Beschreibung", 42, "9238472"); // echtes konto ist
																										// wichtig für
																										// verknüpfung
		String beschreibung = "Schutzgeld";
		String titel = "Titel";
		// mock the data set
		TransaktionDataSet mockDataSet = createMockTransaktionDataSet(betrag, datum, ersteller, zielKonto, beschreibung,
				titel);
		// add and create transaktion

		sp.createAndAddTransaktion(mockDataSet);
		// check that ausgefuehrt ist true
		Transaktion t1 = (Transaktion) sp.getObject(ClassType.TRANSAKTION, "1");
		assertTrue(t1.isAusgefuehrt());
	}

	@Test
	public void testAddingFutureTransaktionWithId() {
		// speicher instanz
		Speicher sp = new Speicher();
		// data
		BigDecimal betrag = new BigDecimal("123");
		Date datum = Date.valueOf("8888-04-04"); // future transaktion
		Nutzer ersteller = mock(Nutzer.class);
		Konto zielKonto = new Konto(new BigDecimal("200"), mock(Nutzer.class), "Name", "Beschreibung", 42, "9238472"); // echtes konto ist
																										// wichtig für
																										// verknüpfung
		String beschreibung = "Schutzgeld";
		String titel = "Titel";
		String id = "42";
		// mock the data set
		TransaktionDataSet mockDataSet = createMockTransaktionDataSet(betrag, datum, ersteller, zielKonto, beschreibung,
				titel);
		when(mockDataSet.get("id")).thenReturn(id);
		when(mockDataSet.hasKey("id")).thenReturn(true);
		// add and create transaktion
		sp.createAndAddTransaktion(mockDataSet);

		// check that ausgefuehrt ist true
		Transaktion t1 = (Transaktion) sp.getObject(ClassType.TRANSAKTION, id);
		assertFalse(t1.isAusgefuehrt());
		// änderung des datums -> transaktion sollte augeführt werden
		sp.updateTrVw(datum);
		assertTrue(t1.isAusgefuehrt());
	}

	private TransaktionDataSet createMockTransaktionDataSet(BigDecimal betrag, Date datum, Nutzer ersteller,
			Konto zielKonto, String beschreibung, String titel) {
		TransaktionDataSet mockDataSet = mock(TransaktionDataSet.class);
		when(mockDataSet.get("betrag")).thenReturn(betrag);
		when(mockDataSet.get("datum")).thenReturn(datum);
		when(mockDataSet.get("ersteller")).thenReturn(ersteller);
		when(mockDataSet.get("zielKonto")).thenReturn(zielKonto);
		when(mockDataSet.get("beschreibung")).thenReturn(beschreibung);
		when(mockDataSet.get("titel")).thenReturn(titel);
		when(mockDataSet.getClassType()).thenReturn(ClassType.TRANSAKTION);
		return mockDataSet;
	}

	private DataSet createMockNutzerDataSet(String vorname, String nachname, int icon) {
		DataSet mockDataSet = mock(DataSet.class);
		when(mockDataSet.get("vorname")).thenReturn(vorname);
		when(mockDataSet.get("nachname")).thenReturn(nachname);
		when(mockDataSet.get("icon")).thenReturn(icon);
		when(mockDataSet.getClassType()).thenReturn(ClassType.NUTZER);
		return mockDataSet;
	}

}
