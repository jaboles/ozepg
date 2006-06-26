//
//  NineMSNEPGGrabber.java
//  ozEPG
//
//  Created by Jonathan Boles on 26/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.net.*;
import java.io.*;

import org.mozilla.javascript.*;

public class NineMSNEPGGrabber implements EPGGrabber {
	private static SimpleDateFormat programmeListUrlDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat programmeDataDateParser = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

	private String[] urls = new String[] {
		"http://tvguide.ninemsn.com.au/search/default.asp?region=$LOCATION$&day=$DATE$&TimeZ=early&type=fta&go.x=9&go.y=11&go=go&search=true",
		"http://tvguide.ninemsn.com.au/search/default.asp?region=$LOCATION$&day=$DATE$&TimeZ=morning&type=fta&go.x=9&go.y=11&go=go&search=true",
		"http://tvguide.ninemsn.com.au/search/default.asp?region=$LOCATION$&day=$DATE$&TimeZ=afternoon&type=fta&go.x=9&go.y=11&go=go&search=true",
		"http://tvguide.ninemsn.com.au/search/default.asp?region=$LOCATION$&day=$DATE$&TimeZ=night&type=fta&go.x=9&go.y=11&go=go&search=true"
	};
	
	private static Pattern obfuscatedDataPattern = Pattern.compile("(?s)criteria.</b><SCRIPT Language=\"Javascript\">(.*?)</SCRIPT></TABLE></td></tr></table>");
	
	private static Pattern programmeDataPattern = Pattern.compile("(?s)<b>(\\d{1,2}:\\d{2} [apm]{2})</b><BR><b>(.*?)(?:<[^>]*?>){3}<a(?:.*?)pid=(\\d*?)\"><b>(.*?)(?:<[^>]*?>){3} \\((\\d*?) mins, Rated:(.*?)\\) </font><br>([^<]*)");
	private static String programmeBaseUrl = "http://tvguide.ninemsn.com.au/cu/default.asp?pid=$ID$";
	private static String programmeDataPatternKey = "TCINLRD";

	private static String[][] deobfuscationReplacements = new String[][] {
		{"location\\.href", "''"},
		{"return;", ";"}, 
		{"document\\.write\\((\\w*?)\\);", "return $1;"},
		{"'\\);(\\w*?)\\('", "')+$1('"}
	};
	
	
	
	public NineMSNEPGGrabber() {
	}
	
	public XMLTVDocument grab(int location, Date date) throws IOException, IllegalArgumentException {
		XMLTVDocument tvDoc = new XMLTVDocument();
		ArrayList programmes = new ArrayList();
		
		
		for (int i = 0; i < urls.length; i++) {
			grabAndAdd(location, date, programmes, urls[i]);
		}
		
		for (int i = 0; i < programmes.size(); i++)
			tvDoc.addProgramme((Programme)programmes.get(i));
		
		tvDoc.toXMLDocument();
		
		return tvDoc;
	}
	
	private void grabAndAdd(int location, Date date, ArrayList programmes, String s) throws IOException, IllegalArgumentException {
		String url = s;
		s = s.replaceAll("\\$LOCATION\\$", URLEncoder.encode(Integer.toString(location)));
		s = s.replaceAll("\\$DATE\\$", URLEncoder.encode(programmeListUrlDateFormatter.format(date)));
		
		String data = URLGrabber.grab(s);
		
		String obfuscatedData = Utils.findMatch(obfuscatedDataPattern, data);
		if (obfuscatedData != null) {
			data = decode(obfuscatedData);
		}
		
		String[][] pd = Utils.findAllMatches(programmeDataPattern, data);
		if (pd == null) {
			throw new IllegalArgumentException("Could not parse downloaded EPG data. Please check for an updated versio of ozEPG!");
		}
		
		for (int i = 0; i < pd.length; i++) {
			Programme p = new Programme();
			for (int j = 0; j < pd[i].length; j++) {
				if (programmeDataPatternKey.charAt(j) == 'T') {
					Date d;
					try {
						d = programmeDataDateParser.parse(programmeListUrlDateFormatter.format(date)+" "+pd[i][j]);
					} catch (ParseException e) {
						throw new IllegalArgumentException("Could not parse date. Please check for an updated version of ozEPG!");
					}
					p.setStartTime(d);
				} else if (programmeDataPatternKey.charAt(j) == 'C') {
					Channel c = ozEPG.getInstance().getChannel(pd[i][j]);
					if (c == null)
						throw new IllegalArgumentException("Unknown channel '"+pd[i][j]+"'. Please check for an updated version of ozEPG!");
					p.setChannel(c);
				} else if (programmeDataPatternKey.charAt(j) == 'I') {
					p.setId(pd[i][j]);
				} else if (programmeDataPatternKey.charAt(j) == 'N') {
					p.setTitle(pd[i][j]);
				} else if (programmeDataPatternKey.charAt(j) == 'L') {
					p.setDuration(Integer.parseInt(pd[i][j]));
				} else if (programmeDataPatternKey.charAt(j) == 'R') {
					p.setRating(pd[i][j]);
				} else if (programmeDataPatternKey.charAt(j) == 'D') {
					p.setDescription(pd[i][j]);
				}
			}
			p.setURL(new URL(programmeBaseUrl.replaceAll("\\$ID\\$", p.getId())));
			programmes.add(p);
		}
	}
	
	private String decode(String s) throws IllegalArgumentException {
		String d = s;
		for (int i = 0; i < deobfuscationReplacements.length; i++) {
			d = d.replaceAll(deobfuscationReplacements[i][0], deobfuscationReplacements[i][1]);
		}
		
		try {
			Context cx = Context.enter();
			Scriptable scope = cx.initStandardObjects();
			return cx.toString(cx.evaluateString(scope, d, "<cmd>", 1, null));
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not evaluate javascript expression. Please check for an updated version of ozEPG!");
		}
	}	
}
