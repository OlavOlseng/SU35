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
		XMLFactory factory = new XMLFactory();
		System.out.println(factory.makeEmployee(meg));
	}

}
