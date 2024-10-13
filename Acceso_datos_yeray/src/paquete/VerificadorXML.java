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
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance(), fabrica2 = DocumentBuilderFactory.newInstance();
		fabrica.setValidating(true);
		fabrica.setIgnoringElementContentWhitespace(true);
		fabrica2.setValidating(false);
		fabrica2.setIgnoringElementContentWhitespace(true);

		try {
			DocumentBuilder constructor = fabrica.newDocumentBuilder();
			DocumentBuilder constructor2 = fabrica2.newDocumentBuilder();
			File xml = new File("tema3_3.xml"), xml2 = new File("tema3_4.xml");
			Document texto = constructor.parse(xml), texto2 = constructor2.parse(xml2);
			XPath ruta =XPathFactory.newInstance().newXPath(), ruta2 =XPathFactory.newInstance().newXPath();
			
			String recorrer = "/class/student", recorrer2 = "/personas/persona";
			NodeList nodos = (NodeList) ruta.compile(recorrer).evaluate(texto, XPathConstants.NODESET), nodosotro = (NodeList) ruta2.compile(recorrer2).evaluate(texto2, XPathConstants.NODESET);
			texto.getDocumentElement().normalize();
			texto2.getDocumentElement().normalize();
			
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
			
			
			for (int z= 0; z < nodosotro.getLength(); z++) {
				    Node nodos3 = nodosotro.item(z);
				    Element objeto2 = (Element) nodos3;

				    System.out.println("\nCurrent Element: " + nodos3.getNodeName());
				    System.out.println("Nombre: " + objeto2.getElementsByTagName("nombre").item(0).getTextContent());
				    System.out.println("Edad: " + objeto2.getElementsByTagName("edad").item(0).getTextContent());

				    Node mascotaNode = objeto2.getElementsByTagName("mascota").item(0);
				        Element mascotaElement = (Element) mascotaNode;
				        
				        System.out.println("Nombre de mascota: " + mascotaElement.getElementsByTagName("nombre").item(0).getTextContent());
				        System.out.println("Tipo de mascota: " + mascotaElement.getElementsByTagName("tipo").item(0).getTextContent());
		
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}
