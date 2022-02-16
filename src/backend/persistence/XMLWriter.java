package backend.persistence;


import java.io.File;
import java.util.List;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import backend.konten.Konto;
import backend.speicher.DataSet;
import backend.speicher.SavableObject;
import backend.speicher.Speicher;
import constants.ClassType;

public class XMLWriter {
	
	public XMLWriter () {
		

	}
	
	public void writeAndSave(Speicher sp) {
		Transformer tr;
		try {
			tr = TransformerFactory.newInstance().newTransformer();
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
		    tr.transform(new DOMSource(this.writeDocument(sp)), new StreamResult(new File("file.xml")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Document writeDocument(Speicher sp) {
		Document dom = null;
		try {
			 DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			 dom = builder.newDocument();
			 
			 //root element
			 Element root = dom.createElement("finanzmanager");
		     dom.appendChild(root);
		     //einzelne Objekte aud Manager, (Konto,Nutzer,Transaktion)
		     root.appendChild(writeClassType(dom, ClassType.NUTZER,sp));
		     root.appendChild(writeClassType(dom, ClassType.TRANSAKTION,sp));
		     root.appendChild(writeClassType(dom, ClassType.KONTO,sp));
		     
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return dom;
	}
	
	
	private Element writeClassType(Document dom, ClassType classType,Speicher sp) {
		Element root = dom.createElement(classType.getDisplayName()+"List");
		
		for(SavableObject n:(List<SavableObject>)(List<?>) sp.getAll(classType)) {
			Element elementRoot = dom.createElement(classType.getDisplayName());
			root.appendChild(elementRoot);
			
			DataSet dataSet = n.getXMLDataSet();
			
			Attr attr = dom.createAttribute("id");
		    attr.setValue((String) dataSet.get("id"));
		    elementRoot.setAttributeNode(attr);
		    
		    for(String key:dataSet.getValues().keySet()) {
		        Element tmp = dom.createElement(key);
		        tmp.setTextContent(dataSet.get(key).toString());
		        elementRoot.appendChild(tmp);
		    }
		}
		 
		 return root;
	}
   
}
