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
	private int id;
	private String name;
	
	public void setId(int i) {
		this.id = i;
	}
	
	public void setName(String s) {
		this.name = s;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
