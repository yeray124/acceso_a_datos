package paquete;

import java.io.File;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class VerificadorXML {

	public static void main(String[] args) throws XPathExpressionException {
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		fabrica.setValidating(true);
		fabrica.setIgnoringElementContentWhitespace(true);

		try {
			DocumentBuilder constructor = fabrica.newDocumentBuilder();
			File xml = new File("tema3_3.xml");
			Document texto = constructor.parse(xml); 
			XPath ruta =XPathFactory.newInstance().newXPath();
			
			String recorrer = "/class/student";
			NodeList nodos = (NodeList) ruta.compile(recorrer).evaluate(texto, XPathConstants.NODESET);
			texto.getDocumentElement().normalize();
			
			for (int i= 0; i < nodos.getLength(); i++) {
				Node nodos2 = nodos.item(i);
				
				System.out.println("\nCurrent Element: " + nodos2.getNodeName());
				Element objeto = (Element) nodos2;
				System.out.println("Student roll no: " + objeto .getAttribute("rollno"));
				System.out.println("Primer nombre: " + objeto.getElementsByTagName("firstname").item(0).getTextContent());
				System.out.println("Ultimo nombre: " + objeto.getElementsByTagName("lastname").item(0).getTextContent());
				System.out.println("Apodo: " + objeto.getElementsByTagName("nickname").item(0).getTextContent());
				System.out.println("Puntos: " + objeto.getElementsByTagName("marks").item(0).getTextContent());
				
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}
