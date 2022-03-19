package test.backend.dataSets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.dataSets.KontoDataSet;
import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.dataSets.TransaktionDataSet;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.constants.ClassType;

public class DataSetTest {
	@Test
	public void testCreationOfDataSetNutzer() {
		/* Test Value */
		String vorname = "Hans";
		String nachname = "meister";
		int icon = 1;
		/* End Test Values */

		NutzerDataSet testSet = new NutzerDataSet(vorname, nachname, icon);
		assertEqualsAllNutzerDataSetValues(testSet, vorname, nachname, icon);

	}

	@Test
	public void testCreationOfDataSetTransaktion() {
		/* Test Value */
		double betrag = 232;
		Date datum = mock(Date.class);
		Nutzer ersteller = mock(Nutzer.class);
		Konto zielKonto = mock(Konto.class);
		String beschreibung = "beschreibung";
		String titel = "titel";
		/* End Test Values */

		TransaktionDataSet testSet = new TransaktionDataSet(betrag, datum, ersteller, zielKonto, beschreibung, titel);
		assertEqualsAllTransaktionDataSetValues(testSet, betrag, datum, ersteller, zielKonto, beschreibung, titel);
	}
	@Test
	public void testCreationOfKontoDataSet() {
		/* Test Value */
		double kontostand = 232;
		Nutzer ersteller = mock(Nutzer.class);
		String beschreibung = "beschreibung";
		String name = "name";
		int icon = 42;
		/* End Test Values */

		KontoDataSet testSet = new KontoDataSet(kontostand, ersteller, name, beschreibung, icon);
		assertEqualsAllKontoDataSetValues(testSet, kontostand, ersteller, name, beschreibung, icon);
	}

	private void assertEqualsAllKontoDataSetValues(KontoDataSet testSet, double kontostand, Nutzer ersteller,
			String name, String beschreibung, int icon) {
		/* Test if all Values are there */
		assertEquals(kontostand, testSet.get("kontostand"));
		assertEquals(ersteller, testSet.get("ersteller"));
		assertEquals(name, testSet.get("name"));
		assertEquals(beschreibung, testSet.get("beschreibung"));
		assertEquals(icon, testSet.get("icon"));
		/* Check if Class Type is Correct */
		assertEquals(ClassType.KONTO, testSet.getClassType());
		/* Check if has Keys works */
		assertEquals(false, testSet.hasKey("imLebenNich"));
		assertEquals(true, testSet.hasKey("kontostand"));
		/* Check if returned HashMap Works */
		HashMap<String, Object> values = testSet.getValues();
		assertEquals(kontostand, values.get("kontostand"));
		assertEquals(ersteller, values.get("ersteller"));
		assertEquals(name, values.get("name"));
		assertEquals(beschreibung, values.get("beschreibung"));
		assertEquals(icon, values.get("icon"));

	}

	private void assertEqualsAllNutzerDataSetValues(NutzerDataSet testSet, String vorname, String nachname, int icon) {
		/* Test if all Values are there */
		assertEquals(vorname, testSet.get("vorname"));
		assertEquals(nachname, testSet.get("nachname"));
		assertEquals(icon, testSet.get("icon"));
		/* Check if Class Type is Correct */
		assertEquals(ClassType.NUTZER, testSet.getClassType());
		/* Check if has Keys works */
		assertEquals(false, testSet.hasKey("imLebenNich"));
		assertEquals(true, testSet.hasKey("vorname"));
		/* Check if returned HashMap Works */
		HashMap<String, Object> values = testSet.getValues();
		assertEquals(vorname, values.get("vorname"));
		assertEquals(nachname, values.get("nachname"));
		assertEquals(icon, values.get("icon"));
	}

	private void assertEqualsAllTransaktionDataSetValues(TransaktionDataSet testSet, double betrag, Date datum,
			Nutzer ersteller, Konto zielKonto, String beschreibung, String titel) {
		/* Test if all Values are there */
		assertEquals(betrag, testSet.get("betrag"));
		assertEquals(datum, testSet.get("datum"));
		assertEquals(ersteller, testSet.get("ersteller"));
		assertEquals(zielKonto, testSet.get("zielKonto"));
		assertEquals(beschreibung, testSet.get("beschreibung"));
		assertEquals(titel, testSet.get("titel"));

		/* Check if Class Type is Correct */
		assertEquals(ClassType.TRANSAKTION, testSet.getClassType());
		/* Check if has Keys works */
		assertEquals(false, testSet.hasKey("imLebenNich"));
		assertEquals(true, testSet.hasKey("datum"));
		/* Check if returned HashMap Works */
		HashMap<String, Object> values = testSet.getValues();
		assertEquals(betrag, values.get("betrag"));
		assertEquals(datum, values.get("datum"));
		assertEquals(ersteller, values.get("ersteller"));
		assertEquals(zielKonto, values.get("zielKonto"));
		assertEquals(beschreibung, values.get("beschreibung"));
		assertEquals(titel, values.get("titel"));
	}

}