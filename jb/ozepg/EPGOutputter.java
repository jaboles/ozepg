//
//  EPGOutputter.java
//  ozEPG
//
//  Created by Jonathan Boles on 26/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package jb.ozepg;
import jb.ozepg.ui.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class EPGOutputter {
	protected final String name;
	protected MainWindow parent;
	protected JCheckBox checkbox;
	protected JPanel panel;
	protected JPanel subPanel;
	protected final JTextField outputParameterTextField;
	
	public EPGOutputter(String name, String outputParameter, String defaultTargetLocation) {
		this.name = name;
		
		panel = new JPanel(new BorderLayout());
		checkbox = new JCheckBox();
		subPanel = new JPanel(new BorderLayout());
		outputParameterTextField = new JTextField();
		
		subPanel.setBorder(new TitledBorder(name));
		
		subPanel.add(new JLabel(outputParameter), BorderLayout.WEST);
		subPanel.add(outputParameterTextField, BorderLayout.CENTER);
		panel.add(subPanel, BorderLayout.CENTER);
		panel.add(checkbox, BorderLayout.WEST);
		
		setEnabled(Settings.getInstance().getOutputterEnabled(name));
		outputParameterTextField.setText(Settings.getInstance().getOutputterTargetLocation(name, defaultTargetLocation));
		
		checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnabled(checkbox.isSelected());
				parent.updateGrabButtonStatus();
				
				Settings.getInstance().putOutputterEnabled(EPGOutputter.this);
			}
		});
		
		outputParameterTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {saveTargetLocationInPrefs();}
			public void removeUpdate(DocumentEvent e) {saveTargetLocationInPrefs();}
			public void insertUpdate(DocumentEvent e) {saveTargetLocationInPrefs();}
		});
	}
	
	public String getName() {
		return name;
	}
	
	public JPanel getPanel(MainWindow parent) {
		this.parent = parent;
		return panel;
	}
	
	public void setEnabled(boolean b) {
		checkbox.setSelected(b);
		panel.setEnabled(b);
		outputParameterTextField.setEnabled(b);
	}
	
	public boolean isEnabled() {
		return panel.isEnabled();
	}
	
	private void saveTargetLocationInPrefs() {
		Settings.getInstance().putOutputterTargetLocation(name, outputParameterTextField.getText());
	}
}
