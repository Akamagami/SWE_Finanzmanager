package test.backend.factories;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.factories.NutzerFactory;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.ElementFactory;

public class TestNutzerFactory {
	 @Test
	 public void testGetAndSetIndex() {
		 ElementFactory testFactory = new NutzerFactory();
		 
		 String testIndex = "42";
		 
		 testFactory.setIndex(testIndex);
		 assertEquals(testFactory.getIndex(), testIndex.toString());
	 }
	 @Test
	 public void testNutzerCreationWithoutId() {
		 //Object Values
		 String vorname = "Heinrich";
		 String nachname = "Hunt";
		 int icon = 42;
		 //mock the data set
		 DataSet mockDataSet = createMockNutzerDataSet(vorname, nachname, icon);
		 //create Object
		 ElementFactory testFactory = new NutzerFactory();
		 
		 Nutzer testNutzer = (Nutzer) testFactory.create(mockDataSet, Optional.empty());
		 assertEqualsAllDataSetValuesForNutzer(testNutzer, mockDataSet);
		 assertEquals(testNutzer.getId(), (Integer.parseInt(testFactory.getIndex())-1)+"");
	 }
	 @Test
	 public void testNutzerCreationWithId() {
		 //Object Values
		 String vorname = "Heinrich";
		 String nachname = "Hunt";
		 int icon = 42;
		 String id = "630";
		 //mock the data set
		 DataSet mockDataSet = createMockNutzerDataSet(vorname, nachname, icon);
		 //create Object
		 ElementFactory testFactory = new NutzerFactory();
		 
		 Nutzer testNutzer = (Nutzer) testFactory.create(mockDataSet, Optional.of(id));
		 assertEqualsAllDataSetValuesForNutzer(testNutzer, mockDataSet);
		 assertEquals(testNutzer.getId(), id);
		 assertEquals(testFactory.getIndex(), (Integer.parseInt(id)+1)+"");
		 
	
	 }
	 @Test
	 public void testNutzerCreationWithLowerId() {
		 //Object Values
		 String vorname = "Heinrich";
		 String nachname = "Hunt";
		 int icon = 42;
		 String id = "20";
		 String newIndex = "42";
		 //mock the data set
		 DataSet mockDataSet = createMockNutzerDataSet(vorname, nachname, icon);
		 //create Object
		 ElementFactory testFactory = new NutzerFactory();
		 testFactory.setIndex(newIndex);
		 //createObject
		 Nutzer testNutzer = (Nutzer) testFactory.create(mockDataSet, Optional.of(id));
		 assertEqualsAllDataSetValuesForNutzer(testNutzer, mockDataSet);;
		 assertEquals(testNutzer.getId(), id);
		 assertEquals(testFactory.getIndex(),newIndex);
	 }
	 private DataSet createMockNutzerDataSet(String vorname, String nachname, int icon) {
		 DataSet mockDataSet = mock(DataSet.class);
		 when(mockDataSet.get("vorname")).thenReturn(vorname);
		 when(mockDataSet.get("nachname")).thenReturn(nachname);
		 when(mockDataSet.get("icon")).thenReturn(icon);
		 return mockDataSet;
	 }
	 private void assertEqualsAllDataSetValuesForNutzer(Nutzer testNutzer, DataSet mockDataSet) {
		 assertEquals(testNutzer.getName().getVorname(),mockDataSet.get("vorname"));
		 assertEquals(testNutzer.getName().getNachname(),mockDataSet.get("nachname"));
		 assertEquals(testNutzer.getIcon(),mockDataSet.get("icon"));
	 }
	 
}
