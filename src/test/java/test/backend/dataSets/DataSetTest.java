package test.backend.dataSets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.constants.ClassType;


public class DataSetTest {
	  @Test
	  public void testCreationOfDataSetNutzer() {
		  /*Test Value*/
		  String vorname = "Hans";
		  String nachname = "meister";
		  int icon = 1;
		  /*End Test Values*/
		  
		 DataSet testSet = new NutzerDataSet(vorname,nachname,icon);
		  
		 /*Test if all Values are there*/
		  assertEquals(vorname, testSet.get("vorname"));
		  assertEquals(nachname, testSet.get("nachname"));
		  assertEquals(icon, testSet.get("icon"));
		 /*Check if Class Type is Correct*/
		  assertEquals(ClassType.NUTZER, testSet.getClassType());
		 /*Check if has Keys works*/
		  assertEquals(false, testSet.hasKey("imLebenNich"));
		  assertEquals(true, testSet.hasKey("vorname"));
		 /*Check if returned HashMap Works*/
		  HashMap<String,Object> values = testSet.getValues();
		  assertEquals(vorname, values.get("vorname"));
		  assertEquals(nachname, values.get("nachname"));
		  assertEquals(icon, values.get("icon"));
	  }
	  public void testCreationOfDataSetTransaktion() {
		  /*Test Value*/
		  String vorname = "Hans";
		  String nachname = "meister";
		  int icon = 1;
		  /*End Test Values*/
		  
		 DataSet testSet = new NutzerDataSet(vorname,nachname,icon);
		  
		 /*Test if all Values are there*/
		  assertEquals(vorname, testSet.get("vorname"));
		  assertEquals(nachname, testSet.get("nachname"));
		  assertEquals(icon, testSet.get("icon"));
		 /*Check if Class Type is Correct*/
		  assertEquals(ClassType.NUTZER, testSet.getClassType());
		 /*Check if has Keys works*/
		  assertEquals(false, testSet.hasKey("imLebenNich"));
		  assertEquals(true, testSet.hasKey("vorname"));
		 /*Check if returned HashMap Works*/
		  HashMap<String,Object> values = testSet.getValues();
		  assertEquals(vorname, values.get("vorname"));
		  assertEquals(nachname, values.get("nachname"));
		  assertEquals(icon, values.get("icon"));
	  }
	  
}