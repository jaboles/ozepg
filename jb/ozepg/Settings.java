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
			new Channel(new String[] {"ABC", "ABC ACT", "ABC NSW", "ABC NT", "ABC QLD", "ABC SA", "ABC TAS"}, new int[] {2, 20}),
			new Channel(new String[] {"ABC2"}, new int[] {2}),
			new Channel(new String[] {"Central GTS/BKN"}, new int[] {2}),
			new Channel(new String[] {"Channel Nine Adelaide"}, new int[] {2}),
			new Channel(new String[] {"Channel Nine Brisbane Metro"}, new int[] {2}),
			new Channel(new String[] {"Channel Nine Darwin"}, new int[] {2}),
			new Channel(new String[] {"Channel Nine Gold Coast"}, new int[] {2}),
			new Channel(new String[] {"Channel Nine Perth"}, new int[] {2}),
			new Channel(new String[] {"Channel Nine Sydney"}, new int[] {2}),
			new Channel(new String[] {"Channel Seven Adelaide"}, new int[] {2}),
			new Channel(new String[] {"Channel Seven Brisbane"}, new int[] {2}),
			new Channel(new String[] {"Channel Seven Central"}, new int[] {2}),
			new Channel(new String[] {"Channel Seven Darwin"}, new int[] {2}),
			new Channel(new String[] {"Channel Seven Melbourne"}, new int[] {2}),
			new Channel(new String[] {"Channel Seven Perth"}, new int[] {2}),
			new Channel(new String[] {"Channel Seven Queensland, Cairns/Townsville/Mackay"}, new int[] {2}),
			new Channel(new String[] {"Channel Seven Queensland, Rockhampton/Toowoomba"}, new int[] {2}),
			new Channel(new String[] {"Channel Seven Sydney"}, new int[] {2}),
			new Channel(new String[] {"Imparja Television NSW/VIC"}, new int[] {2}),
			new Channel(new String[] {"Imparja Television NT"}, new int[] {2}),
			new Channel(new String[] {"Imparja Television QLD"}, new int[] {2}),
			new Channel(new String[] {"NBN"}, new int[] {2}),
			new Channel(new String[] {"NBN Gold Coast"}, new int[] {2}),
			new Channel(new String[] {"Network TEN Adelaide"}, new int[] {2}),
			new Channel(new String[] {"Network TEN Brisbane"}, new int[] {2}),
			new Channel(new String[] {"Network TEN Digital, Mildura"}, new int[] {2}),
			new Channel(new String[] {"Network TEN Perth"}, new int[] {2}),
			new Channel(new String[] {"Network TEN Sydney"}, new int[] {2}),
			new Channel(new String[] {"Prime Gold Coast"}, new int[] {2}),
			new Channel(new String[] {"Prime Griffith"}, new int[] {2}),
			new Channel(new String[] {"Prime Northern"}, new int[] {2}),
			new Channel(new String[] {"Prime Northern, Tamworth/Taree/Lismore/Coffs Harbour"}, new int[] {2}),
			new Channel(new String[] {"Prime Northern, Newcastle"}, new int[] {2}),
			new Channel(new String[] {"Prime Southern, Canberra/Wollongong/Sth Coast"}, new int[] {2}),
			new Channel(new String[] {"Prime Southern, Wagga Wagga/Orange"}, new int[] {2}),
			new Channel(new String[] {"SBS Eastern"}, new int[] {2}),
			new Channel(new String[] {"SBS News"}, new int[] {33}),
			new Channel(new String[] {"SBS NT"}, new int[] {33}),
			new Channel(new String[] {"SBS Queensland"}, new int[] {33}),
			new Channel(new String[] {"SBS SA"}, new int[] {33}),
			new Channel(new String[] {"SBS Sydney"}, new int[] {33}),
			new Channel(new String[] {"SBS Tasmania"}, new int[] {33}),
			new Channel(new String[] {"SBS WA"}, new int[] {33}),
			new Channel(new String[] {"Southern Cross Darwin"}, new int[] {2}),
			new Channel(new String[] {"Southern Cross TEN"}, new int[] {2}),
			new Channel(new String[] {"Southern Cross TEN Capital, Canberra"}, new int[] {2}),
			new Channel(new String[] {"Southern Cross TEN Capital, Wagga"}, new int[] {2}),
			new Channel(new String[] {"Southern Cross TEN Northern NSW, Non Gold Coast"}, new int[] {2}),
			new Channel(new String[] {"Southern Cross TEN Northern NSW, Gold Coast"}, new int[] {2}),
			new Channel(new String[] {"Southern Cross TEN Queensland"}, new int[] {2}),
			new Channel(new String[] {"WIN Television Griffith"}, new int[] {2}),
			new Channel(new String[] {"WIN Television NSW"}, new int[] {2}),
			new Channel(new String[] {"WIN Television QLD, Mackay/Wide Bay"}, new int[] {2}),
			new Channel(new String[] {"WIN Television QLD, Regional Qld"}, new int[] {2}),
			new Channel(new String[] {"WIN Television SA"}, new int[] {2}),
			new Channel(new String[] {"WIN Television South East SA"}, new int[] {2}),
			new Channel(new String[] {"WIN TEN"}, new int[] {2}),
			new Channel(new String[] {"ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZYACKKK"}, new int[] {2})
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
