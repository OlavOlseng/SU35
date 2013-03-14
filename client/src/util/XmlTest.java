package util;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import nu.xom.ParsingException;
import models.Employee;

public class XmlTest {

	public static void main(String[] args) {
		Employee meg = new Employee("sindrma@gmail.com", "Sindre", "Magnussen", 
				"69167663", "41517055");
		XMLFactory.createNewObject("employee");
		XMLFactory.addChild("email", meg.getEmail());
		XMLFactory.addChild("firstname", meg.getFirstName());
		XMLFactory.addChild("lastname", meg.getLastName());
		XMLFactory.addChild("homephone", meg.getHomePhone());
		XMLFactory.addChild("mobilephone", meg.getMobilePhone());
		System.out.println(XMLFactory.getXML());
	}

}
