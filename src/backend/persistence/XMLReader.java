package backend.persistence;

import java.io.File;
import java.sql.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import backend.dataSets.KontoDataSet;
import backend.dataSets.NutzerDataSet;
import backend.dataSets.TransaktionDataSet;
import backend.konten.Konto;
import backend.nutzer.Nutzer;
import backend.speicher.*;
import constants.ClassType;

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
				 sp.createObject(dataSet);
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
		ret.addKey("aktiv",element.getElementsByTagName("aktiv").item(0).getTextContent() );
		String mitgliederString = (String) element.getElementsByTagName("beschreibung").item(0).getTextContent();
		
		
		return null;
	}

	private DataSet createTransaktionDataSet(Element element) {
		
		TransaktionDataSet ret = new TransaktionDataSet(
			Double.parseDouble(element.getElementsByTagName("betrag").item(0).getTextContent()),
			(Date) Date.valueOf(element.getElementsByTagName("datume").item(0).getTextContent()),
			(Nutzer) sp.getObject(ClassType.NUTZER, element.getElementsByTagName("ersteller").item(0).getTextContent()),
			(Konto) null,
			(String) element.getElementsByTagName("beschreibung").item(0).getTextContent(),
			(String) element.getElementsByTagName("titel").item(0).getTextContent());
		
		ret.addKey("id", element.getElementsByTagName("id").item(0).getTextContent());
		ret.addKey("ausgefuehrt", element.getElementsByTagName("ausgefuehrt").item(0).getTextContent());
		ret.addKey("obsolet", element.getElementsByTagName("obsolet").item(0).getTextContent());
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
}
