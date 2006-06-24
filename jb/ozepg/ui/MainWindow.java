//
//  MainWindow.java
//  ozEPG
//
//  Created by Jonathan Boles on 23/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg.ui;
import org.swixml.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import jb.ozepg.*;

public class MainWindow {
	private SwingEngine renderer;
	private JFrame window;
	public JComboBox locationComboBox;
	public JDateSpinner dateSpinner;
	public JLabel versionLabel;
	
	public MainWindow(LocationList locationList) {
		renderer = new EnhancedSwingEngine(this);
		try {
			window = (JFrame)renderer.render(MainWindow.class.getResource("/xml/mainwindow.xml"));
		} catch (Exception e) {
			System.err.println(e);
			ozEPG.getInstance().crashed("Couldn't load mainwindow.xml");
		}
		for (int i = 0; i < locationList.size(); i++) locationComboBox.addItem(locationList.get(i));
		versionLabel.setFont(versionLabel.getFont().deriveFont(10f));
		versionLabel.setText("Version: "+ozEPG.getVersion()+"    ");
		window.pack();
		window.show();
	}
	
	public Action grabAction = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			Location selectedLocation = (Location)locationComboBox.getSelectedItem();
			
			EPGGrabber grabber = new EPGGrabber();
			try {
				grabber.grab(selectedLocation.getId(), (Date)dateSpinner.getValue());
			} catch (IOException ex) {
				ex.printStackTrace(System.err);
			}
		}
	};
}
