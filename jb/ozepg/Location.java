//
//  Location.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.io.Serializable;

public class Location implements Serializable {
	private String ninemsnId;
	private String name;
	
	public Location(String name, String ninemsnId) {
		this.name = name;
		this.ninemsnId = ninemsnId;
	}
	
	public void setNinemsnId(String s) {
		this.ninemsnId = s;
	}
	public void setName(String s) {
		this.name = s;
	}
	
	public String getNinemsnId() {
		return ninemsnId;
	}
	public String getName() {
		return name;
	}
	
	/** Synonym for getName() */
	public String toString() {
		return name;
	}
}
