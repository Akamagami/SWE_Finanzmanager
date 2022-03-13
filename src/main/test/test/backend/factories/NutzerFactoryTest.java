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

public class NutzerFactoryTest {
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
		 DataSet mockDataSet = mock(DataSet.class);
		 when(mockDataSet.get("vorname")).thenReturn(vorname);
		 when(mockDataSet.get("nachname")).thenReturn(nachname);
		 when(mockDataSet.get("icon")).thenReturn(icon);
		 //create Object
		 ElementFactory testFactory = new NutzerFactory();
		 
		 Nutzer testNutzer = (Nutzer) testFactory.create(mockDataSet, Optional.empty());
		 assertEquals(testNutzer.getName().getVorname(),vorname);
		 assertEquals(testNutzer.getName().getNachname(),nachname);
		 assertEquals(testNutzer.getIcon(),icon);
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
		 DataSet mockDataSet = mock(DataSet.class);
		 when(mockDataSet.get("vorname")).thenReturn(vorname);
		 when(mockDataSet.get("nachname")).thenReturn(nachname);
		 when(mockDataSet.get("icon")).thenReturn(icon);
		 //create Object
		 ElementFactory testFactory = new NutzerFactory();
		 
		 Nutzer testNutzer = (Nutzer) testFactory.create(mockDataSet, Optional.of(id));
		 assertEquals(testNutzer.getName().getVorname(),vorname);
		 assertEquals(testNutzer.getName().getNachname(),nachname);
		 assertEquals(testNutzer.getIcon(),icon);
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
		 DataSet mockDataSet = mock(DataSet.class);
		 when(mockDataSet.get("vorname")).thenReturn(vorname);
		 when(mockDataSet.get("nachname")).thenReturn(nachname);
		 when(mockDataSet.get("icon")).thenReturn(icon);
		 //create Object
		 ElementFactory testFactory = new NutzerFactory();
		 testFactory.setIndex(newIndex);
		 
		 Nutzer testNutzer = (Nutzer) testFactory.create(mockDataSet, Optional.of(id));
		 assertEquals(testNutzer.getName().getVorname(),vorname);
		 assertEquals(testNutzer.getName().getNachname(),nachname);
		 assertEquals(testNutzer.getIcon(),icon);
		 assertEquals(testNutzer.getId(), id);
		 assertEquals(testFactory.getIndex(),newIndex);
	 }
	 
	 
}
