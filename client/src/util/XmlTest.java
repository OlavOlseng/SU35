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
		try {
			String xml = XMLSerializer.personToXml(meg);
			System.out.println(xml);
			Employee emp = XMLSerializer.toPerson(xml);
			System.out.println(emp);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
