//
//  Utils.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.util.regex.*;

public class Utils {
	public static String findMatch(Pattern p, String text) {
		Matcher m = p.matcher(text);
		if (m.find()) {
			return m.group(1);
		} else {
			return null;
		}
	}
	
	public static String[][] findAllMatches(Pattern p, String text) {
		Matcher m = p.matcher(text);
		int matchCount = 0;		
		while (m.find()) matchCount++;
		
		if (matchCount == 0) {
			return null;
		} else {
			m.reset();
			String[][] s = new String[matchCount][m.groupCount()];
			for (int i = 0; m.find(); i++) {
				for (int j = 0; j < m.groupCount(); j++) {
					s[i][j] = m.group(j+1);
				}
			}
			return s;
		}
	}
}
