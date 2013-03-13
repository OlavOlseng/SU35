package util;


import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.*;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

import models.Employee;

public class XMLSerializer {
	
	public static String personToXml(Employee emp) throws ParserConfigurationException {
				
		Element rootEmp = new Element("employee");
		
		Element firstname = new Element("firstname");
		firstname.appendChild(emp.getFirstName());
		rootEmp.appendChild(firstname);
		
		Element lastname = new Element("lastname");
		lastname.appendChild(emp.getLastName());
		rootEmp.appendChild(lastname);
		
		
		Element homephone = new Element("homephone");
		homephone.appendChild(emp.getHomePhone());
		rootEmp.appendChild(homephone);
		
		Element mobilephone = new Element("mobilephone");
		mobilephone.appendChild(emp.getMobilePhone());
		rootEmp.appendChild(mobilephone);
		
		Document doc = new Document(rootEmp);
		return doc.toXML();
	}
	
	public static Employee toPerson(String xml) throws IOException, ParseException, ParsingException {
		Builder parser = new Builder(false);
		Document doc = parser.build(xml, null);
		return assemblePerson(doc.getRootElement());
		}
	
	private static Employee assemblePerson(Element personElement) throws ParseException {
		String firstname = null, lastname = null, 
				email = null, homephone = null, mobilephone = null;
		
		Element element = personElement.getFirstChildElement("email");
		if (element != null) {
			email = element.getValue();
		}
		
		element = personElement.getFirstChildElement("firstname");
		if (element != null) {
			firstname = element.getValue();
		}
		
		element = personElement.getFirstChildElement("lastname");
		if (element != null) {
			lastname = element.getValue();
		}
		
		element = personElement.getFirstChildElement("homephone");
		if (element != null) {
			homephone = element.getValue();
		}
		
		element = personElement.getFirstChildElement("mobilephone");
		if (element != null) {
			mobilephone = element.getValue();
		}
		
		return new Employee(email, firstname, lastname, homephone, mobilephone);
	}
}


