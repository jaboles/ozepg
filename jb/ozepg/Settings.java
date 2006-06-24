//
//  Settings.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.util.regex.*;

public class Settings {
	private static Settings singleton;
	
	public String getLocationListUrl() {return "http://tvguide.ninemsn.com.au/default.asp?type=fta";}
	public Pattern getLocationListPattern() {return Pattern.compile("(?s)<SELECT name=\"region\" onchange=\"setlocation\\(this\\);\">(.*?)</select>");}
	public Pattern getLocationItemPattern() {return Pattern.compile("<option value=(\\d*?) (?:SELECTED)?>(.*?)</option>");}
	public String getLocationItemPatternKey() {return "IN";}
	
	
	private String[] defaultProgramListUrls = new String[] {
		"http://tvguide.ninemsn.com.au/search/default.asp?region=$LOCATION$&day=$DATE$&TimeZ=early&type=fta&go.x=9&go.y=11&go=go&search=true",
		"http://tvguide.ninemsn.com.au/search/default.asp?region=$LOCATION$&day=$DATE$&TimeZ=morning&type=fta&go.x=9&go.y=11&go=go&search=true",
		"http://tvguide.ninemsn.com.au/search/default.asp?region=$LOCATION$&day=$DATE$&TimeZ=afternoon&type=fta&go.x=9&go.y=11&go=go&search=true",
		"http://tvguide.ninemsn.com.au/search/default.asp?region=$LOCATION$&day=$DATE$&TimeZ=night&type=fta&go.x=9&go.y=11&go=go&search=true"
	};
	public int getProgramListUrlCount() {return defaultProgramListUrls.length;}
	public String getProgramListUrl(int index) {return defaultProgramListUrls[index];}
	public String getProgramListUrlDateFormat() {return "dd/MM/yyyy";}
	
	private String[][] defaultDeobfuscationReplacements = new String[][] {
		{"location\\.href", "''"},
		{"return;", ";"}, 
		{"document\\.write\\((\\w*?)\\);", "return $1;"},
		{"'\\);(\\w*?)\\('", "')+$1('"}
	};
	public Pattern getObfuscatedDataPattern() {return Pattern.compile("(?s)criteria.</b><SCRIPT Language=\"Javascript\">(.*?)</SCRIPT></TABLE></td></tr></table>");}
	public int getDeobfuscationReplacementCount() {return defaultDeobfuscationReplacements.length;}
	public String getDeobfuscationString(int i) {return defaultDeobfuscationReplacements[i][0];}
	public String getDeobfuscationReplacement(int i) {return defaultDeobfuscationReplacements[i][1];}
	public String getDeobfuscationFormat() {return "js";}
	
	public Pattern getProgrammeDataPattern() {return Pattern.compile("(?s)<b>(\\d{1,2}:\\d{2} [apm]{2})</b><BR><b>(.*?)(?:<[^>]*?>){3}<a(?:.*?)pid=(\\d*?)\"><b>(.*?)(?:<[^>]*?>){3} \\((\\d*?) mins, Rated:(.*?)\\) </font><br>([^<]*)");}
	public String getProgrammeDataPatternKey() {return "TCINLRD";}
	public String getProgrammeDataDateFormat() {return "dd/MM/yyyy hh:mm aa";}
	public String getProgrammeBaseUrl() {return "http://tvguide.ninemsn.com.au/cu/default.asp?pid=$ID$";}
	
	public Channel[] getChannels() {
		return new Channel[] {
			new Channel(new String[] {"ABC2"}, new int[] {2}),
			new Channel(new String[] {"SBS News"}, new int[] {2}),
			new Channel(new String[] {"Channel Seven Melbourne"}, new int[] {2})
		};
	}
	
	
	public static Settings getInstance() {
		if (singleton == null) singleton = new Settings();
		return singleton;
	}
	
	private Settings() {
	}
}
