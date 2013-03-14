package models;

import java.text.ParseException;
import java.util.HashMap;
import util.XMLAssembler;
import nu.xom.Document;
import nu.xom.Element;

public class ApplicationModel {
	private HashMap<String, Employee> employees;
	
	public ApplicationModel(){
		employees = new HashMap<String, Employee>();
	}
	
	public void updateObject(Document d) throws ParseException{
		Element e = d.getRootElement();
		switch(e.getLocalName()){
		case "employee":
			updateEmployee(e);
		default:
			return;
		}
	}
	
	public void updateEmployee(Element element) throws ParseException{
		Element email = element.getFirstChildElement("email");
		Employee savedEmployee = employees.get(email.getValue());
		if(savedEmployee == null){
			return;
		}
		Employee employee = XMLAssembler.assembleEmployee(element, savedEmployee);
		
	}
	
}
