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
	protected MainWindow parent;
	protected JCheckBox checkbox;
	protected JPanel panel;
	protected JPanel subPanel;
	protected JTextField outputParamterTextField;
	
	public EPGOutputter(String name, String outputParameter) {
		panel = new JPanel(new BorderLayout());
		checkbox = new JCheckBox();
		subPanel = new JPanel(new BorderLayout());
		outputParamterTextField = new JTextField();
		
		subPanel.setBorder(new TitledBorder(name));
		
		subPanel.add(new JLabel(outputParameter), BorderLayout.WEST);
		subPanel.add(outputParamterTextField, BorderLayout.CENTER);
		panel.add(subPanel, BorderLayout.CENTER);
		panel.add(checkbox, BorderLayout.WEST);
		
		setEnabled(false);
		
		checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnabled(checkbox.isSelected());
				parent.updateGrabButtonStatus();
			}
		});
	}
	
	public JPanel getPanel(MainWindow parent) {
		this.parent = parent;
		return panel;
	}
	
	public void setEnabled(boolean b) {
		panel.setEnabled(b);
		outputParamterTextField.setEnabled(b);
	}
	
	public boolean isEnabled() {
		return panel.isEnabled();
	}
}
