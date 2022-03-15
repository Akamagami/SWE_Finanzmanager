package test.backend.factories;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.factories.KontoFactory;
import com.example.swe_finanzmanager.backend.factories.NutzerFactory;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;


public class KontoFactoryTest {
	@Test
	 public void testGetAndSetIndex() {
		 ElementFactory testFactory = new KontoFactory();
		 
		 String testIndex = "42";
		 
		 testFactory.setIndex(testIndex);
		 assertEquals(testFactory.getIndex(), testIndex.toString());
	 }
	 @Test
	 public void testKontoCreationWithoutId() {
		 //Object Values
		 double kontostand = 3423;
		 Nutzer ersteller = mock(Nutzer.class);
		 String name = "KontoName";
		 String beschreibung = "KontoBeschreibung";
		 int icon = 42;
		 //mock the data set
		 DataSet mockDataSet = mock(DataSet.class);
		 when(mockDataSet.get("kontostand")).thenReturn(kontostand);
		 when(mockDataSet.get("ersteller")).thenReturn(ersteller);
		 when(mockDataSet.get("name")).thenReturn(name);
		 when(mockDataSet.get("beschreibung")).thenReturn(beschreibung);
		 when(mockDataSet.get("icon")).thenReturn(icon);
		 //create Object
		 ElementFactory testFactory = new KontoFactory();
		 
		 Konto testKonto = (Konto) testFactory.create(mockDataSet, Optional.empty());
		 assertEquals(testKonto.getKontostand(),kontostand);
		 assertEquals(testKonto.getErsteller(),ersteller);
		 assertEquals(testKonto.getName(),name);
		 assertEquals(testKonto.getBeschreibung(),beschreibung);
		 assertEquals(testKonto.getIcon(),icon);
		 assertEquals(testKonto.getId(), (Integer.parseInt(testFactory.getIndex())-1)+"");
	 }
	 @Test
	 public void testKontoCreationWithIdAndValues() {
		//Object Values
		 double kontostand = 2423;
		 Nutzer ersteller = mock(Nutzer.class);
		 String name = "KontoName";
		 String beschreibung = "KontoBeschreibung";
		 int icon = 42;
		 String id ="42212";
		 boolean aktiv = true;
		 List<Nutzer>  mitgliedList = new ArrayList<Nutzer>();
		 List<Transaktion> tList = new ArrayList<Transaktion>();
		 //fill mock lists
		 mitgliedList.add(mock(Nutzer.class));
		 mitgliedList.add(mock(Nutzer.class));
		 tList.add(mock(Transaktion.class));
		 tList.add(mock(Transaktion.class));
		 //mock the data set
		 DataSet mockDataSet = mock(DataSet.class);
		 when(mockDataSet.get("kontostand")).thenReturn(kontostand);
		 when(mockDataSet.get("ersteller")).thenReturn(ersteller);
		 when(mockDataSet.get("name")).thenReturn(name);
		 when(mockDataSet.get("beschreibung")).thenReturn(beschreibung);
		 when(mockDataSet.get("icon")).thenReturn(icon);
		 when(mockDataSet.get("id")).thenReturn(id);
		 when(mockDataSet.get("aktiv")).thenReturn(aktiv);
		 when(mockDataSet.get("mitgliedList")).thenReturn(mitgliedList);
		 when(mockDataSet.get("tList")).thenReturn(tList);
		 //add extra values
		 when(mockDataSet.hasKey("aktiv")).thenReturn(true);
		 when(mockDataSet.hasKey("mitgliedList")).thenReturn(true);
		 when(mockDataSet.hasKey("tList")).thenReturn(true);
		 //create Object
		 ElementFactory testFactory = new KontoFactory();
		 //check values
		 Konto testKonto = (Konto) testFactory.create(mockDataSet, Optional.of(id));
		 assertEquals(testKonto.getKontostand(),kontostand);
		 assertEquals(testKonto.getErsteller(),ersteller);
		 assertEquals(testKonto.getName(),name);
		 assertEquals(testKonto.getBeschreibung(),beschreibung);
		 assertEquals(testKonto.getIcon(),icon);
		 assertEquals(testKonto.isAktiv(),aktiv);
		 assertEquals(testKonto.getId(), id);
		 assertEquals(testFactory.getIndex(), (Integer.parseInt(id)+1)+"");
		 //check if all list vallues are added
		 for(Nutzer n:mitgliedList) {
			 assertEquals(testKonto.nutzerIstMitglied(n),true);
		 }
		 assertEquals(testKonto.gettList().equals(tList),true);
	 }
	 @Test
	 public void testKontoCreationWithoutLowerId() {
		 //Object Values
		 double kontostand = 234234;
		 Nutzer ersteller = mock(Nutzer.class);
		 String name = "KontoName";
		 String beschreibung = "KontoBeschreibung";
		 int icon = 42;
		 String id = "20";
		 //raise id index
		 String testIndex = "42";
		 
		 
		 //mock the data set
		 DataSet mockDataSet = mock(DataSet.class);
		 when(mockDataSet.get("kontostand")).thenReturn(kontostand);
		 when(mockDataSet.get("ersteller")).thenReturn(ersteller);
		 when(mockDataSet.get("name")).thenReturn(name);
		 when(mockDataSet.get("beschreibung")).thenReturn(beschreibung);
		 when(mockDataSet.get("icon")).thenReturn(icon);
		 //create Object
		 ElementFactory testFactory = new KontoFactory();
		 testFactory.setIndex(testIndex);
		 Konto testKonto = (Konto) testFactory.create(mockDataSet, Optional.of(id));
		 assertEquals(testKonto.getKontostand(),kontostand);
		 assertEquals(testKonto.getErsteller(),ersteller);
		 assertEquals(testKonto.getName(),name);
		 assertEquals(testKonto.getBeschreibung(),beschreibung);
		 assertEquals(testKonto.getIcon(),icon);
		 assertEquals(testKonto.getId(), id);
		 assertEquals(testFactory.getIndex(),testIndex);
	 }
	 
	 
	 
}
