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
		NodeList ndList = docmt.getElementsByTagName("item");      
	   for (int tempval = 0; tempval < ndList.getLength(); tempval++) {
	       Node nd = ndList.item(tempval);
	       if (nd.getNodeType() == Node.ELEMENT_NODE) {
	           Element elemnt = (Element) nd;
	           //System.out.println("Id : " + elemnt.getAttribute("empid"));
	           System.out.println("Id: " + elemnt.getElementsByTagName("id").item(0).getTextContent());
	           System.out.println("Firstname: " + elemnt.getElementsByTagName("firstName").item(0).getTextContent());
	           System.out.println("Lastname: " + elemnt.getElementsByTagName("lastName").item(0).getTextContent());
			   System.out.println("email: " + elemnt.getElementsByTagName("emailId").item(0).getTextContent() + "\n");
		   }
		}
	}

	public int toInt(String num) {
		return Integer.parseInt(num);
	}
}