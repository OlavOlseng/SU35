package util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMImplementation;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class XMLFactory {
	
	private static Element root;
	
	public static void createNewObject(String name) {
		root = new Element(name);
	}
	
	public static void addChild(String name, String value, String ...values) {
		Element node = new Element(name);
		node.appendChild(value);
		for (String s : values) {
			node.appendChild(s);
		}
		root.appendChild(node);
	}
	
	public static String getXML() {
		Document d = new Document(root);
		root = null;
		return d.toXML();
	}
	
	
}
