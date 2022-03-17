package test.backend.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.dataSets.KontoDataSet;
import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.factories.KontoFactory;
import com.example.swe_finanzmanager.backend.factories.NutzerFactory;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;

public class TestKontoFactory {
	@Test
	public void testGetAndSetIndex() {
		ElementFactory testFactory = new KontoFactory();

		String testIndex = "42";

		testFactory.setIndex(testIndex);
		assertEquals(testFactory.getIndex(), testIndex.toString());
	}

	@Test
	public void testKontoCreationWithoutId() {
		// Object Values
		double kontostand = 3423;
		Nutzer ersteller = mock(Nutzer.class);
		String name = "KontoName";
		String beschreibung = "KontoBeschreibung";
		int icon = 42;
		// mock the data set
		DataSet mockDataSet = createMockKontoDataSet(kontostand, ersteller, name, beschreibung, icon);
		// create Object
		ElementFactory testFactory = new KontoFactory();

		Konto testKonto = (Konto) testFactory.create(mockDataSet, Optional.empty());
		assertEqualsAllDataSetValuesForKonto(testKonto, mockDataSet);
		assertEquals(testKonto.getId(), (Integer.parseInt(testFactory.getIndex()) - 1) + "");
	}

	@Test
	public void testKontoCreationWithIdAndValues() {
		// Object Values
		double kontostand = 2423;
		Nutzer ersteller = mock(Nutzer.class);
		String name = "KontoName";
		String beschreibung = "KontoBeschreibung";
		int icon = 42;
		String id = "42212";
		boolean aktiv = true;
		List<Nutzer> mitgliedList = new ArrayList<Nutzer>();
		List<Transaktion> tList = new ArrayList<Transaktion>();
		// fill mock lists
		mitgliedList.add(mock(Nutzer.class));
		mitgliedList.add(mock(Nutzer.class));
		tList.add(mock(Transaktion.class));
		tList.add(mock(Transaktion.class));
		// mock the data set
		DataSet mockDataSet = createMockKontoDataSetWithExtraValues(kontostand, ersteller, name, beschreibung, icon,
				aktiv, mitgliedList, tList);
		// create Object
		ElementFactory testFactory = new KontoFactory();
		// check values
		Konto testKonto = (Konto) testFactory.create(mockDataSet, Optional.of(id));
		assertEqualsAllDataSetValuesForKonto(testKonto, mockDataSet);
		assertEquals(testKonto.getId(), id);
		assertEquals(testFactory.getIndex(), (Integer.parseInt(id) + 1) + "");

	}

	@Test
	public void testKontoCreationWithoutLowerId() {
		// Object Values
		double kontostand = 234234;
		Nutzer ersteller = mock(Nutzer.class);
		String name = "KontoName";
		String beschreibung = "KontoBeschreibung";
		int icon = 42;
		String id = "20";
		// raise id index
		String testIndex = "42";

		// mock the data set
		DataSet mockDataSet = createMockKontoDataSet(kontostand, ersteller, name, beschreibung, icon);
		// create Object
		ElementFactory testFactory = new KontoFactory();
		testFactory.setIndex(testIndex);
		Konto testKonto = (Konto) testFactory.create(mockDataSet, Optional.of(id));
		assertEqualsAllDataSetValuesForKonto(testKonto, mockDataSet);
		assertEquals(testKonto.getId(), id);
		assertEquals(testFactory.getIndex(), testIndex);
	}

	private DataSet createMockKontoDataSet(double kontostand, Nutzer ersteller, String name, String beschreibung,
			int icon) {
		DataSet mockDataSet = mock(KontoDataSet.class);
		when(mockDataSet.get("kontostand")).thenReturn(kontostand);
		when(mockDataSet.get("ersteller")).thenReturn(ersteller);
		when(mockDataSet.get("name")).thenReturn(name);
		when(mockDataSet.get("beschreibung")).thenReturn(beschreibung);
		when(mockDataSet.get("icon")).thenReturn(icon);
		return mockDataSet;
	}

	private DataSet createMockKontoDataSetWithExtraValues(double kontostand, Nutzer ersteller, String name,
			String beschreibung, int icon, boolean aktiv, List<Nutzer> mitgliedList, List<Transaktion> tList) {
		DataSet mockDataSet = createMockKontoDataSet(kontostand, ersteller, name, beschreibung, icon);

		when(mockDataSet.get("aktiv")).thenReturn(aktiv);
		when(mockDataSet.get("mitgliedList")).thenReturn(mitgliedList);
		when(mockDataSet.get("tList")).thenReturn(tList);

		when(mockDataSet.hasKey("aktiv")).thenReturn(true);
		when(mockDataSet.hasKey("mitgliedList")).thenReturn(true);
		when(mockDataSet.hasKey("tList")).thenReturn(true);
		return mockDataSet;
	}

	private void assertEqualsAllDataSetValuesForKonto(Konto testKonto, DataSet mockDataSet) {
		assertEquals(testKonto.getKontostand(), mockDataSet.get("kontostand"));
		assertEquals(testKonto.getErsteller(), mockDataSet.get("ersteller"));
		assertEquals(testKonto.getName(), mockDataSet.get("name"));
		assertEquals(testKonto.getBeschreibung(), mockDataSet.get("beschreibung"));
		assertEquals(testKonto.getIcon(), mockDataSet.get("icon"));
		/* optional Values */
		if (mockDataSet.hasKey("aktiv")) {
			assertEquals(testKonto.isAktiv(), mockDataSet.get("aktiv"));
		}
		if (mockDataSet.hasKey("mitgliedList")) {
			for (Nutzer n : (List<Nutzer>) (List<?>) mockDataSet.get("mitgliedList")) {
				assertTrue(testKonto.nutzerIstMitglied(n));
			}
		}
		if (mockDataSet.hasKey("tList")) {
			assertTrue(testKonto.gettList().equals(mockDataSet.get("tList")));
		}
	}

}
