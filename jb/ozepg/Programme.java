//
//  Programme.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.net.*;
import java.util.*;
import org.jdom.*;

public class Programme {
	private String id;
	private String title;
	private Channel channel;
	private Date startTime;
	private String description;
	private int duration;
	private String rating;
	private URL url;
	
	public void setId(String s) {
		id = s;
	}
	
	public void setTitle(String s) {
		title = s;
	}
	
	public void setChannel(Channel s) {
		channel = s;
	}
	
	public void setStartTime(Date d) {
		startTime = d;
	}
	
	public void setDescription(String s) {
		description = s;
	}
	
	public void setDuration(int i) {
		duration = i;
	}
	
	public void setRating(String s) {
		rating = s;
	}
	
	public void setURL(URL u) {
		url = u;
	}
	
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public String getRating() {
		return rating;
	}
	
	public URL getURL() {
		return url;
	}
	
	public Element toXMLElement() {
		Element e = new Element("programme");
		e.setAttribute("channel", channel.getPrimaryName());
		//e.setAttribute("start", null);
		//e.setAttribute("stop", null);
		e.addContent((Element)new Element("title").addContent(title));
		e.addContent((Element)new Element("sub-title"));
		e.addContent((Element)new Element("desc").addContent(description));
		e.addContent((Element)new Element("rating").setAttribute("system", "ABA").addContent((Element)new Element("value").addContent(rating)));
		e.addContent((Element)new Element("length").setAttribute("units", "minutes").addContent(Integer.toString(duration)));
		e.addContent((Element)new Element("category"));
		e.addContent((Element)new Element("url").addContent(url.toString()));
		
		return e;
	}
}
