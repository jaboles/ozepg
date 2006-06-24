//
//  URLGrabber.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import java.io.*;
import java.net.*;


public class URLGrabber {
	public static String grab(String url) throws IOException {
		return grab(connect(url));
	}
	
	public static String grab(URL url) throws IOException {
		return grab(connect(url));
	}
	
    public static String grab(URLConnection conn) throws IOException {
        byte[] buf = new byte[8192];
        StringBuffer sbuf = new StringBuffer();
        InputStream is = conn.getInputStream();
        for (int readCount = 0; (readCount = is.read(buf)) != -1; sbuf.append(new String(buf, 0, readCount)));
        return sbuf.toString();
    }
    
    public static void post(URLConnection conn, String data) throws IOException {
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        byte[] buf = data.getBytes();
        os.write(buf, 0, buf.length);
        os.close();
    }
    
    public static URLConnection connect(String url) throws IOException, MalformedURLException {
        return connect(new URL(url));
    }
	
	public static URLConnection connect(URL url) throws IOException {
		URLConnection uc = url.openConnection();
		uc.addRequestProperty("Referer", url.toString());
		uc.connect();
		return uc;
	}
    
    public static String encode(String data) {
        String enc = "";
        try {
            enc = URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {}
        return enc;
    }
}
