//
//  LocationList.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;

public class LocationList {
	public static final String CACHE_FILENAME = "locations.cache";
	
	private ArrayList locations;
	
	public LocationList() throws IOException, IllegalArgumentException {
		locations = new ArrayList();
		String data = URLGrabber.grab(Settings.getInstance().getLocationListUrl());
		
		String locationList = Utils.findMatch(Settings.getInstance().getLocationListPattern(), data);
		if (locationList == null) throw new IllegalArgumentException("No location list found at url.");
		
		String[][] s = Utils.findAllMatches(Settings.getInstance().getLocationItemPattern(), locationList);
		if (s == null) throw new IllegalArgumentException("No location items found in location list.");
		
		for (int i = 0; i < s.length; i++) {
			Location loc = new Location();
			for(int j = 0; j < s[i].length; j++) {
				if (Settings.getInstance().getLocationItemPatternKey().charAt(j) == 'I') {
					loc.setId(Integer.parseInt(s[i][j]));
				} else if (Settings.getInstance().getLocationItemPatternKey().charAt(j) == 'N') {
					loc.setName(s[i][j]);
				} else {
					throw new IllegalArgumentException("Invalid character found in location list key descriptor");
				}
			}
			locations.add(loc);
		}	

		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(CACHE_FILENAME));
			os.writeObject(locations);
			os.close();
		} catch (IOException e) {
			System.err.println("Warning: couldn't cache location list!");
		}
		
	}
	
	public LocationList(File f) throws IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(CACHE_FILENAME));
		locations = (ArrayList)is.readObject();
		is.close();
	}
	
	public int size() {
		return locations.size();
	}
	
	public Location get(int index) {
		return (Location)locations.get(index);
	}
}
