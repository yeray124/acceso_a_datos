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

public class Ejercicio3 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		fabrica.setValidating(true);
		fabrica.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            File archivo = new File("tema3_3.xml");
            Document word = constructor.parse(archivo);
            word.getDocumentElement().normalize();
            XPath nodos = XPathFactory.newInstance().newXPath();
            String busqueda = "/class/student";
            NodeList listaNodos = (NodeList) nodos.compile(busqueda).evaluate(word, XPathConstants.NODESET);
            for (int i = 0; i < listaNodos.getLength(); i++) {
                Node nodos2 = listaNodos.item(i);
                System.out.println("\nCurrent Elemento: " + nodos2.getNodeName());
                Element elemento = (Element) nodos2;
                System.out.println("Estudiante roll no: " + elemento.getAttribute("rollno"));
                System.out.println("Primer nombre: " + elemento.getElementsByTagName("primernombre").item(0).getTextContent());
            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}