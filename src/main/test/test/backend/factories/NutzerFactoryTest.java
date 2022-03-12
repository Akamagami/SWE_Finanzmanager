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
		 DataSet mockDataSet = mock(NutzerDataSet.class);
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
		 DataSet mockDataSet = mock(NutzerDataSet.class);
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
		 
		 //Second Object with smaller id to make sure that the factory indes does not change
		 String vorname2 = "Hans";
		 String nachname2 = "Peter";
		 int icon2 = 43;
		 String id2 = "42";
		 DataSet mockDataSet2 = mock(NutzerDataSet.class);
		 when(mockDataSet2.get("vorname")).thenReturn(vorname2);
		 when(mockDataSet2.get("nachname")).thenReturn(nachname2);
		 when(mockDataSet2.get("icon")).thenReturn(icon2);
		 //create Object
		 Nutzer testNutzer2 = (Nutzer) testFactory.create(mockDataSet2, Optional.of(id2));
		 assertEquals(testNutzer2.getName().getVorname(),vorname2);
		 assertEquals(testNutzer2.getName().getNachname(),nachname2);
		 assertEquals(testNutzer2.getIcon(),icon2);
		 assertEquals(testNutzer2.getId(), id2);
		 assertEquals(testFactory.getIndex(), (Integer.parseInt(id)+1)+"");
	 }
	 
	 
}
