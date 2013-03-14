package util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import javax.xml.parsers.*;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

import models.Appointment;
import models.Employee;

public class XMLAssembler {
	
	public static Employee assembleEmployee(Element personElement, Employee e) throws ParseException {
		String firstname = null, lastname = null, 
				email = null, homephone = null, mobilephone = null;
		
		Element element = personElement.getFirstChildElement("email");
		if (element != null) {
			e.setEmail(element.getValue());
		}
		element = personElement.getFirstChildElement("firstname");
		if (element != null) {
			e.setFirstName(element.getValue());
		}
		element = personElement.getFirstChildElement("lastname");
		if (element != null) {
			e.setLastName(element.getValue());
		}
		element = personElement.getFirstChildElement("homephone");
		if (element != null) {
			e.setHomePhone(element.getValue());
		}
		
		element = personElement.getFirstChildElement("mobilephone");
		if (element != null) {
			e.setMobilePhone(element.getValue());
		}
		return new Employee(email, firstname, lastname, homephone, mobilephone);
	}
	

}


