//
//  EPGGrabber.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

import org.mozilla.javascript.*;

public class EPGGrabber {
	private SimpleDateFormat programmeListUrlDateFormatter;
	private SimpleDateFormat programmeDataDateParser;
	
	public EPGGrabber() {
		programmeListUrlDateFormatter = new SimpleDateFormat(Settings.getInstance().getProgramListUrlDateFormat());
		programmeDataDateParser = new SimpleDateFormat(Settings.getInstance().getProgrammeDataDateFormat());
	}
	
	public XMLTVDocument grab(int location, Date date) throws IOException, IllegalArgumentException {
		XMLTVDocument tvDoc = new XMLTVDocument();
		ArrayList programmes = new ArrayList();
		
		
		for (int i = 0; i < Settings.getInstance().getProgramListUrlCount(); i++) {
			grabAndAdd(location, date, programmes, Settings.getInstance().getProgramListUrl(i));
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
		
		String obfuscatedData = Utils.findMatch(Settings.getInstance().getObfuscatedDataPattern(), data);
		if (obfuscatedData != null) {
			data = decode(obfuscatedData);
		}
		
		String[][] pd = Utils.findAllMatches(Settings.getInstance().getProgrammeDataPattern(), data);
		if (pd == null) {
			throw new IllegalArgumentException("Could not parse downloaded EPG data. Please check for an updated versio of ozEPG!");
		}
		
		String orderKey = Settings.getInstance().getProgrammeDataPatternKey();
		for (int i = 0; i < pd.length; i++) {
			Programme p = new Programme();
			for (int j = 0; j < pd[i].length; j++) {
				if (orderKey.charAt(j) == 'T') {
					Date d;
					try {
						d = programmeDataDateParser.parse(programmeListUrlDateFormatter.format(date)+" "+pd[i][j]);
					} catch (ParseException e) {
						throw new IllegalArgumentException("Could not parse date. Please check for an updated version of ozEPG!");
					}
					p.setStartTime(d);
				} else if (orderKey.charAt(j) == 'C') {
					p.setChannel(pd[i][j]);
				} else if (orderKey.charAt(j) == 'I') {
					p.setId(pd[i][j]);
				} else if (orderKey.charAt(j) == 'N') {
					p.setTitle(pd[i][j]);
				} else if (orderKey.charAt(j) == 'L') {
					p.setDuration(Integer.parseInt(pd[i][j]));
				} else if (orderKey.charAt(j) == 'R') {
					p.setRating(pd[i][j]);
				} else if (orderKey.charAt(j) == 'D') {
					p.setDescription(pd[i][j]);
				}
			}
			p.setURL(new URL(Settings.getInstance().getProgrammeBaseUrl().replaceAll("\\$ID\\$", p.getId())));
			programmes.add(p);
		}
	}
	
	private String decode(String s) throws IllegalArgumentException {
		String d = s;
		for (int i = 0; i < Settings.getInstance().getDeobfuscationReplacementCount(); i++) {
			d = d.replaceAll(Settings.getInstance().getDeobfuscationString(i), Settings.getInstance().getDeobfuscationReplacement(i));
		}
		
		if (Settings.getInstance().getDeobfuscationFormat().equals("js")) {
			try {
				Context cx = Context.enter();
				Scriptable scope = cx.initStandardObjects();
				return cx.toString(cx.evaluateString(scope, d, "<cmd>", 1, null));
			} catch (Exception e) {
				throw new IllegalArgumentException("Could not evaluate javascript expression. Please check for an updated version of ozEPG!");
			}
		} else if (Settings.getInstance().getDeobfuscationFormat().equals("html")) {
			return d;
		} else {
			return "";
		}
	}
}
