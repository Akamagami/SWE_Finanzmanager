package backend.persistence;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import backend.speicher.*;

public class XMLReader {

	private String path = "";
	
	public XMLReader(String path){
		this.path = path;
	}
	
	public void readAndLoad(Speicher sp) {
		try {
			File inputFile = new File(path);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
