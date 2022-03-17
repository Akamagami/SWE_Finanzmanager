package test.backend.speicher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
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
		DataSet mockDataSet = mock(NutzerDataSet.class);
		when(mockDataSet.get("vorname")).thenReturn(vorname);
		when(mockDataSet.get("nachname")).thenReturn(nachname);
		when(mockDataSet.getClassType()).thenReturn(ClassType.NUTZER);
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
		DataSet mockDataSet = mock(NutzerDataSet.class);
		when(mockDataSet.get("vorname")).thenReturn(vorname);
		when(mockDataSet.get("nachname")).thenReturn(nachname);
		when(mockDataSet.get("icon")).thenReturn(icon);
		when(mockDataSet.getClassType()).thenReturn(ClassType.NUTZER);
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
	 * Check special methods to ad transaktion, since they neec to be added to the
	 * Transaktionsverwaltung aswell
	 */
	
	
}
