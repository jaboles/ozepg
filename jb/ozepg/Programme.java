//
//  Programme.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.util.*;

public class Programme {
	private String id;
	private String title;
	private String channel;
	private Date startTime;
	private String description;
	private int duration;
	private String rating;
	
	public void setId(String s) {
		id = s;
	}
	
	public void setTitle(String s) {
		title = s;
	}
	
	public void setChannel(String s) {
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
	
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getChannel() {
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
}
