package main.java.xmldomparser;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParser {

	private String url;

	public DomParser(String url) {
		this.url = url;
	}

	public void parse() throws Exception {

		DocumentBuilderFactory dbldrFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbldrFactory.newDocumentBuilder();
		URLConnection urlConnection = new URL(url).openConnection();		
	    urlConnection.addRequestProperty("Accept", "application/xml");
		Document docmt = docBuilder.parse(urlConnection.getInputStream());
		docmt.getDocumentElement().normalize();
		System.out.println("Nombre del archivo:" + docmt.getDocumentElement().getNodeName());

		NodeList ndList = docmt.getElementsByTagName("country");

		for (int tempval = 0; tempval < ndList.getLength(); tempval++) {
			Node nd = ndList.item(tempval);
			if (nd.getNodeType() == Node.ELEMENT_NODE) {
				String nmale = "0", nfemale = "0";
				Element elemnt = (Element) nd;
				System.out.println("País: " + elemnt.getAttribute("name"));
				nmale = elemnt.getElementsByTagName("pobmale").item(0).getTextContent();
				System.out.println("Población masculina: " + nmale);
				nfemale = elemnt.getElementsByTagName("pobfemale").item(0).getTextContent();
				System.out.println("Población femenina: : " + nfemale);
				System.out.println("Población total: : " + (toInt(nmale) + toInt(nfemale)) + "\n");
			}
		}
	}

	public int toInt(String num) {
		return Integer.parseInt(num);
	}
}