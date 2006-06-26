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
	private ArrayList epgOutputterList;
	private SwingEngine renderer;
	private JFrame window;
	public JComboBox locationComboBox;
	public JDateSpinner dateSpinner;
	public JLabel versionLabel;
	public JButton grabButton;
	public Box epgOutputterPanel;
	
	public MainWindow(LocationList locationList, ArrayList epgOutputterList) {
		this.renderer = new EnhancedSwingEngine(this);
		this.epgOutputterList = epgOutputterList;
		try {
			window = (JFrame)renderer.render(MainWindow.class.getResource("/xml/mainwindow.xml"));
		} catch (Exception e) {
			System.err.println(e);
			ozEPG.getInstance().crashed("Couldn't load mainwindow.xml");
		}
		for (int i = 0; i < locationList.size(); i++) locationComboBox.addItem(locationList.get(i));
		versionLabel.setFont(versionLabel.getFont().deriveFont(10f));
		versionLabel.setText("Version: "+ozEPG.getVersion()+"    ");
		
		for (int i = 0; i < epgOutputterList.size(); i++) {
			epgOutputterPanel.add((JPanel)((EPGOutputter)epgOutputterList.get(i)).getPanel(this));
		}
		
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
				JOptionPane.showMessageDialog(window, "Couldn't download EPG source data.\n\nPlease check that your computer is connected to the Internet.");
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace(System.err);
				JOptionPane.showMessageDialog(window, ex.toString());
			}
		}
	};
	
	public void updateGrabButtonStatus() {
		for (int i = 0; i < epgOutputterList.size(); i++) {
			EPGOutputter eo = (EPGOutputter)epgOutputterList.get(i);
			if (eo.isEnabled()) {
				grabButton.setEnabled(true);
				return;
			}
		}
		grabButton.setEnabled(false);
	}
}
