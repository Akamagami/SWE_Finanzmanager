package com.example.swe_finanzmanager.backend.persistence;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.swe_finanzmanager.backend.dataSets.KontoDataSet;
import com.example.swe_finanzmanager.backend.dataSets.NutzerDataSet;
import com.example.swe_finanzmanager.backend.dataSets.TransaktionDataSet;
import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;
import com.example.swe_finanzmanager.backend.speicher.DataSet;
import com.example.swe_finanzmanager.backend.speicher.Speicher;
import com.example.swe_finanzmanager.constants.ClassType;

public class XMLReader {

	private String path = "";
	private Speicher sp = null;
	
	public XMLReader(String path){
		this.path = path;
	}
	
	public void readAndLoad(Speicher sp) {
		this.sp = sp;
		try {
			File inputFile = new File(path);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        //create all Objects
	        readAndLoad(sp, doc, ClassType.NUTZER);
	        readAndLoad(sp, doc, ClassType.TRANSAKTION);
	        readAndLoad(sp, doc, ClassType.KONTO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void readAndLoad(Speicher sp, Document doc,ClassType classType) {
		
		NodeList nList = doc.getElementsByTagName(classType.getDisplayName());
		
		for(int i = 0; i< nList.getLength(); i++) {
			 Node node = nList.item(i);
			 
			 if (node.getNodeType() == Node.ELEMENT_NODE){
				 DataSet dataSet = createDataSetFromNode((Element) node, classType);
				 if(classType == ClassType.TRANSAKTION) {
					 sp.createAndAddTransaktion((TransaktionDataSet) dataSet);
				 } else {
					 sp.createObject(dataSet);
				 }
				 
			 }
					 
		}
	}
	
	private DataSet createDataSetFromNode(Element element, ClassType classType) {
		DataSet ret = null;
		
		switch(classType) {
		
			case NUTZER: ret = createNutzerDataSet(element); 
				break;
			case TRANSAKTION: ret = createTransaktionDataSet(element); 
				break;
			case KONTO: ret = createKontoDataSet(element);
				break;
		}
		return ret;
	}

	private DataSet createKontoDataSet(Element element) {
		KontoDataSet ret = new KontoDataSet(
				Double.parseDouble(element.getElementsByTagName("kontostand").item(0).getTextContent()),
				(Nutzer) sp.getObject(ClassType.NUTZER, element.getElementsByTagName("ersteller").item(0).getTextContent()),
				(String) element.getElementsByTagName("name").item(0).getTextContent(),
				(String) element.getElementsByTagName("beschreibung").item(0).getTextContent(),
				Integer.parseInt(element.getElementsByTagName("icon").item(0).getTextContent()));
		
		ret.addKey("id", element.getElementsByTagName("id").item(0).getTextContent());
		ret.addKey("aktiv",(element.getElementsByTagName("aktiv").item(0).getTextContent() == "true"));
		
		String mitgliederString = (String) element.getElementsByTagName("mitgliedList").item(0).getTextContent();
		String tListString = (String) element.getElementsByTagName("tList").item(0).getTextContent();
		
		ret.addKey("mitgliedList", this.getNutzerFromString(mitgliederString));
		ret.addKey("tList", this.getTransaktionenFromString(tListString));
		
		return ret;
	}

	private DataSet createTransaktionDataSet(Element element) {
		
		TransaktionDataSet ret = new TransaktionDataSet(
			Double.parseDouble(element.getElementsByTagName("betrag").item(0).getTextContent()),
			(Date) Date.valueOf(element.getElementsByTagName("datum").item(0).getTextContent()),
			(Nutzer) sp.getObject(ClassType.NUTZER, element.getElementsByTagName("ersteller").item(0).getTextContent()),
			(Konto) new Konto(0, null, path, path, 0, path),//////Kontos sind noch nicht geladen-> wird später eingefüllt
			(String) element.getElementsByTagName("beschreibung").item(0).getTextContent(),
			(String) element.getElementsByTagName("titel").item(0).getTextContent());
		
		ret.addKey("id", element.getElementsByTagName("id").item(0).getTextContent() );
		ret.addKey("ausgefuehrt", (element.getElementsByTagName("ausgefuehrt").item(0).getTextContent() == "true"));
		ret.addKey("obsolet", (element.getElementsByTagName("obsolet").item(0).getTextContent()== "true"));
		return ret;
	}

	private DataSet createNutzerDataSet(Element element) {
		
		NutzerDataSet ret = new NutzerDataSet(
			(String) element.getElementsByTagName("vorname").item(0).getTextContent(),
			(String) element.getElementsByTagName("nachname").item(0).getTextContent(),
			Integer.parseInt(element.getElementsByTagName("icon").item(0).getTextContent()));
		
		ret.addKey("id", element.getElementsByTagName("id").item(0).getTextContent());
		return ret;
	}
	
	private List<Nutzer> getNutzerFromString(String strg){
		List<Nutzer> ret = new ArrayList<Nutzer>();
		
		strg = strg.substring(1, strg.length() - 1);
		strg = strg.replaceAll("\\s+","");
		String[] nutzerList = strg.split(",");
		
		for(String s:nutzerList) {
			ret.add((Nutzer) sp.getObject(ClassType.NUTZER,s));
		}
	return ret;	
	}
	private List<Transaktion> getTransaktionenFromString(String strg){
		List<Transaktion> ret = new ArrayList<Transaktion>();
		
		strg = strg.substring(1, strg.length() - 1);
		strg = strg.replaceAll("\\s+","");
		String[] nutzerList = strg.split(",");
		
		for(String s:nutzerList) {
			ret.add((Transaktion) sp.getObject(ClassType.TRANSAKTION,s));
		}
	return ret;	
	}
	
}
