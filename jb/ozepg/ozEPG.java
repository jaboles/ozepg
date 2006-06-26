//
//  ozEPG.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright (c) 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.util.*;
import java.io.*;

public class ozEPG {
	private static ozEPG singleton;
	private LocationList locationList;
	private jb.ozepg.ui.MainWindow mainWindow;
	public static final String REVISION_STRING = "$Rev$";
	public static final String DATE_STRING = "$Date$";
	private Map channelMap;
	private ArrayList epgOutputters;
	
	public void executeUi() {
		fetchLocationList();
		mainWindow = new jb.ozepg.ui.MainWindow(locationList, epgOutputters);
	}
	
	public void fetchLocationList() {
		try {
			locationList = new LocationList(new File(LocationList.CACHE_FILENAME));
		} catch (Exception e) {
			System.err.println("Warning: couldn't load location list from cache, trying online.");
			try {
				locationList = new LocationList();
			} catch (IOException e2) {
				ozEPG.getInstance().crashed("Couldn't access online TV guide.");
			}
		}
	}
	
	public Channel getChannel(String name) {
		return (Channel)channelMap.get(name);
	}
	
	private ozEPG() {
		Channel[] channels = Settings.getInstance().getChannels();
		channelMap = new HashMap();
		epgOutputters = new ArrayList();
		
		epgOutputters.add(new XMLTVEPGOutputter());
		epgOutputters.add(new NebulaDigiTVEPGOutputter());
		
		for (int i = 0; i < channels.length; i++) {
			for (int j = 0; j < channels[i].getAllNames().length; j++) {
				channelMap.put(channels[i].getAllNames()[j], channels[i]);
			}
		}
	}
	
	public static ozEPG getInstance() {
		return singleton;
	}

    public static void main (String args[]) {
        singleton = new jb.ozepg.ozEPG();
		singleton.executeUi();
    }
	
	public void crashed(String reason) {
		System.err.println("Error: "+reason);
		System.exit(1);
	}
	
	public static String getVersion() {
		String rev = Utils.findMatch(java.util.regex.Pattern.compile("\\$Rev: (\\d+) \\$"), REVISION_STRING);
		String date = Utils.findMatch(java.util.regex.Pattern.compile("\\$Date: (?:.*?)\\((.*?)\\) \\$"), DATE_STRING);
		return rev + " (" + date + ")";
	}
}
