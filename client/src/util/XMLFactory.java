package util;

import nu.xom.Document;
import nu.xom.Element;

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
