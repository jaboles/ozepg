//
//  Channel.java
//  ozEPG
//
//  Created by Jonathan Boles on 25/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;

public class Channel {
	private String[] names;
	private int[] lcns;
	
	public Channel(String[] s, int[] i) {
		this.names = s;
		this.lcns = i;
	}
	
	public String getPrimaryName() {
		return names[0];
	}
	
	public String[] getAllNames() {
		return names;
	}
}
