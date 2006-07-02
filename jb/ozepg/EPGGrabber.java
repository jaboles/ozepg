//
//  EPGGrabber.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.util.*;
import java.io.*;

public interface EPGGrabber {
	public XMLTVDocument grab(String locationId, Date date) throws IOException;
}
