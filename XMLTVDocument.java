//
//  XMLTVDocument.java
//  ozEPG
//
//  Created by Jonathan Boles on 25/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.util.*;
import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

public class XMLTVDocument {
	private ArrayList programmeList;
	private ArrayList channelList;
	
	public XMLTVDocument() {
		channelList = new ArrayList();
		programmeList = new ArrayList();
	}
	
	public void addProgramme(Programme p) {
		programmeList.add(p);
	}
	
	public Document toXMLDocument() {
		Document doc = new Document();
		doc.setRootElement(toXMLElement());
		
		XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
		try {
			xo.output(doc, System.out);
		} catch (IOException e) {};
		
		return doc;
	}
	
	private Element toXMLElement() {
		Element e = new Element("tv");
		
		for (int i = 0; i < programmeList.size(); i++)
			e.addContent(((Programme)programmeList.get(i)).toXMLElement());
		
		return e;
	}
}
