package test.backend.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.factories.NutzerFactory;
import com.example.swe_finanzmanager.backend.factories.TransaktionFactory;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;

public class TestTransaktionFactory {
	 @Test
	 public void testGetAndSetIndex() {
		 ElementFactory testFactory = new TransaktionFactory();
		 
		 String testIndex = "42";
		 
		 testFactory.setIndex(testIndex);
		 assertEquals(testFactory.getIndex(), testIndex.toString());
	 }
	 @Test
	 public void testTransaktionCreationWithoutId() {
		 //Object Values
		 double betrag = 122;
		 Date datum = Date.valueOf("2020-11-10");
		 Nutzer ersteller = mock(Nutzer.class);
		 Konto zielKonto = mock(Konto.class);
		 String beschreibung = "Schutzgeld";
		 String titel = "Titel";
		 //mock the data set
		 DataSet mockDataSet = mock(DataSet.class);
		 when(mockDataSet.get("betrag")).thenReturn(betrag);
		 when(mockDataSet.get("datum")).thenReturn(datum);
		 when(mockDataSet.get("ersteller")).thenReturn(ersteller);
		 when(mockDataSet.get("zielKonto")).thenReturn(zielKonto);
		 when(mockDataSet.get("beschreibung")).thenReturn(beschreibung);
		 when(mockDataSet.get("titel")).thenReturn(titel);

		 //create Object
		 ElementFactory testFactory = new TransaktionFactory();
		 
		 Transaktion testTransaktion = (Transaktion) testFactory.create(mockDataSet, Optional.empty());
		 assertEquals(testTransaktion.getBetrag(),betrag);
		 assertEquals(testTransaktion.getDatum(),datum);
		 assertEquals(testTransaktion.getErsteller(),ersteller);
		 assertEquals(testTransaktion.getZielKonto(),zielKonto);
		 assertEquals(testTransaktion.getBeschreibung(),beschreibung);
		 assertEquals(testTransaktion.getTitel(),titel);
		 assertEquals(testTransaktion.getId(), (Integer.parseInt(testFactory.getIndex())-1)+"");
	 }
	 @Test
	 public void testTransaktionCreationWithIdAndValues() {
		//Object Values
		 double betrag = 122;
		 Date datum = Date.valueOf("2020-11-10");
		 Nutzer ersteller = mock(Nutzer.class);
		 Konto zielKonto = mock(Konto.class);
		 String beschreibung = "Schutzgeld";
		 String titel = "Titel";
		 String id = "309832";
		 boolean ausgefuehrt = false;
		 boolean obsolet = false;
		 //mock the data set
		 DataSet mockDataSet = mock(DataSet.class);
		 when(mockDataSet.get("betrag")).thenReturn(betrag);
		 when(mockDataSet.get("datum")).thenReturn(datum);
		 when(mockDataSet.get("ersteller")).thenReturn(ersteller);
		 when(mockDataSet.get("zielKonto")).thenReturn(zielKonto);
		 when(mockDataSet.get("beschreibung")).thenReturn(beschreibung);
		 when(mockDataSet.get("titel")).thenReturn(titel);
		 when(mockDataSet.get("ausgefuehrt")).thenReturn(ausgefuehrt);
		 when(mockDataSet.get("obsolet")).thenReturn(obsolet);
		 //add key values to .has
		 when(mockDataSet.hasKey("ausgefuehrt")).thenReturn(true);
		 when(mockDataSet.hasKey("obsolet")).thenReturn(true);
		 //create Object
		 ElementFactory testFactory = new TransaktionFactory();
		 
		 Transaktion testTransaktion = (Transaktion) testFactory.create(mockDataSet, Optional.of(id));
		 assertEquals(testTransaktion.getBetrag(),betrag);
		 assertEquals(testTransaktion.getDatum(),datum);
		 assertEquals(testTransaktion.getErsteller(),ersteller);
		 assertEquals(testTransaktion.getZielKonto(),zielKonto);
		 assertEquals(testTransaktion.getBeschreibung(),beschreibung);
		 assertEquals(testTransaktion.getTitel(),titel);
		 assertEquals(testTransaktion.getId(), id);
		 assertEquals(testFactory.getIndex(),(Integer.parseInt(id)+1)+"");
	 }
	 @Test
	 public void testTransaktionCreationWithLowerId() {
		 //Object Values
		 double betrag = 122;
		 Date datum = Date.valueOf("2020-11-10");
		 Nutzer ersteller = mock(Nutzer.class);
		 Konto zielKonto = mock(Konto.class);
		 String beschreibung = "Schutzgeld";
		 String titel = "Titel";
		 String newIndex = "98740";
		 String id = "42";
		 //mock the data set
		 DataSet mockDataSet = mock(DataSet.class);
		 when(mockDataSet.get("betrag")).thenReturn(betrag);
		 when(mockDataSet.get("datum")).thenReturn(datum);
		 when(mockDataSet.get("ersteller")).thenReturn(ersteller);
		 when(mockDataSet.get("zielKonto")).thenReturn(zielKonto);
		 when(mockDataSet.get("beschreibung")).thenReturn(beschreibung);
		 when(mockDataSet.get("titel")).thenReturn(titel);

		 //create Object
		 ElementFactory testFactory = new TransaktionFactory();
		 testFactory.setIndex(newIndex);
		 Transaktion testTransaktion = (Transaktion) testFactory.create(mockDataSet, Optional.of(id));
		 assertEquals(testTransaktion.getBetrag(),betrag);
		 assertEquals(testTransaktion.getDatum(),datum);
		 assertEquals(testTransaktion.getErsteller(),ersteller);
		 assertEquals(testTransaktion.getZielKonto(),zielKonto);
		 assertEquals(testTransaktion.getBeschreibung(),beschreibung);
		 assertEquals(testTransaktion.getTitel(),titel);
		 assertEquals(testTransaktion.getId(), id);
		 assertEquals(testFactory.getIndex(),newIndex);
	 } 
}
