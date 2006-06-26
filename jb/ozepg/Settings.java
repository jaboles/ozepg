//
//  Settings.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.util.*;
import java.util.regex.*;
import java.util.prefs.*;

public class Settings {
	private static Settings singleton;
	private Preferences prefs; 
	
	public String getLocationListUrl() {return "http://tvguide.ninemsn.com.au/default.asp?type=fta";}
	public Pattern getLocationListPattern() {return Pattern.compile("(?s)<SELECT name=\"region\" onchange=\"setlocation\\(this\\);\">(.*?)</select>");}
	public Pattern getLocationItemPattern() {return Pattern.compile("<option value=(\\d*?) (?:SELECTED)?>(.*?)</option>");}
	public String getLocationItemPatternKey() {return "IN";}
	
	
	public Channel[] getChannels() {
		return new Channel[] {
			// Digital
			new Channel(new String[] {"ABC", "ABC ACT", "ABC NSW", "ABC NT", "ABC QLD", "ABC SA", "ABC TAS", "ABC VIC", "ABC WA"}, new int[] {2, 20}),
			new Channel(new String[] {"ABC2"}, new int[] {21}),
			new Channel(new String[] {"Channel Nine","Channel Nine Adelaide","Channel Nine Brisbane Metro","Channel Nine Darwin","Channel Nine Gold Coast","Channel Nine Melbourne","Channel Nine Perth","Channel Nine Sydney"}, new int[] {9, 90}),
			new Channel(new String[] {"Channel Seven","Channel Seven Adelaide","Channel Seven Brisbane","Channel Seven Central","Channel Seven Darwin","Channel Seven Melbourne","Channel Seven Perth","Channel Seven Queensland, Cairns/Townsville/Mackay","Channel Seven Queensland, Rockhampton/Toowoomba","Channel Seven Sydney"}, new int[] {7, 70}),
			new Channel(new String[] {"NBN/WIN","NBN","NBN Gold Coast","WIN Television Griffith","WIN Television Mildura","WIN Television NSW","WIN Television QLD, Mackay/Wide Bay","WIN Television QLD, Regional Qld","WIN Television SA","WIN Television South East SA","WIN Television TAS","WIN Television VIC","WIN Television WA","WIN TEN"}, new int[] {8, 80}),
			new Channel(new String[] {"Network TEN", "Network TEN Adelaide", "Network TEN Brisbane", "Network TEN Digital, Mildura", "Network TEN Melbourne", "Network TEN Perth", "Network TEN Sydney"}, new int[] {1, 10, 12, 100}),
			new Channel(new String[] {"Prime","Golden West Network","Prime Gold Coast","Prime Griffith","Prime Northern","Prime Northern, Tamworth/Taree/Lismore/Coffs Harbour","Prime Northern, Newcastle","Prime Southern, Canberra/Wollongong/Sth Coast","Prime Southern, Wagga Wagga/Orange","Prime Television, Regional Victoria"}, new int[] {6, 60}),
			new Channel(new String[] {"SBS","SBS Eastern","SBS Melbourne","SBS NT","SBS Queensland","SBS SA","SBS Sydney","SBS Tasmania","SBS WA"}, new int[] {3, 30}),
			new Channel(new String[] {"SBS News"}, new int[] {33}),
			new Channel(new String[] {"Southern Cross TEN","Southern Cross Darwin","Southern Cross Television","Southern Cross TEN Capital, Canberra","Southern Cross TEN Capital, Wagga","Southern Cross TEN Northern NSW, Non Gold Coast","Southern Cross TEN Northern NSW, Gold Coast","Southern Cross TEN Queensland","Southern Cross TEN Victoria"}, new int[] {5, 50}),
			new Channel(new String[] {"Tasmanian Digital Television"}, new int[] {5, 50}),
			
			// Non-digital/Unknown
			new Channel(new String[] {"Access 31"}, new int[] {-1}),
			new Channel(new String[] {"Central GTS/BKN"}, new int[] {-1}),
			new Channel(new String[] {"Imparja Television NSW/VIC"}, new int[] {-1}),
			new Channel(new String[] {"Imparja Television NT"}, new int[] {-1}),
			new Channel(new String[] {"Imparja Television QLD"}, new int[] {-1}),
			new Channel(new String[] {"(null)"}, new int[] {-1})
		};
	}
	
	public void putLocationComboBoxIndex(int i) {
		prefs.putInt("location_combo_box_index", i);
	}
	public int getLocationComboBoxIndex() {
		return prefs.getInt("location_combo_box_index", 0);
	}
	
	public void putDaysToGrab(long l) {
		prefs.putLong("days_to_grab", l);
	}
	public long getDaysToGrab() {
		return prefs.getLong("days_to_grab", 1);
	}
	
	public void putOutputterEnabled(EPGOutputter e) {
		prefs.putBoolean("outputter_selected_"+e.getName(), e.isEnabled());
	}
	public boolean getOutputterEnabled(String name) {
		return prefs.getBoolean("outputter_selected_"+name, false);
	}
	
	public void putOutputterTargetLocation(String name, String location) {
		prefs.put("outputter_target_location_"+name, location);
	}
	public String getOutputterTargetLocation(String name, String defaultLocation) {
		return prefs.get("outputter_target_location_"+name, defaultLocation);
	}
	
	
	
	
	public static Settings getInstance() {
		if (singleton == null) singleton = new Settings();
		return singleton;
	}
	
	private Settings() {
		prefs = Preferences.userNodeForPackage(this.getClass());
	}
}
