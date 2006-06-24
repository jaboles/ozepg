//
//  JLongIntegerSpinner.java
//  Caduceus
//
//  Copyright 2005 ICP Firefly Caduceus Development Team (Z. Benitez, J. Boles, L. Constantinescu, M. Lunney).
//	All rights reserved.
//
package jb.ozepg.ui;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
* A JSpinner-based control which stores and displays long integers.
 * <br><br><b>Note:</b> This does not actually constrain the value stored in it to being a Long, but returns always a Long value when queried.
 * This constraint must be handled elsewhere, if desired.
 *
 * <div style="border: double solid black 3px; padding: 5px" class="TableHeadingColor">
 * {@link au.com.icpfirefly.client.ui.util.EnhancedSwingEngine}: Inserts this component into each SwiXML window that needs it, based on an XML UI definition. <hr>
 * {@link au.com.icpfirefly.client.ui.advsearch.AdvancedSearchItemPanel}: The advanced search panel, which makes common use of this class to search by long integer. <hr>
 * {@link au.com.icpfirefly.common.db.DBTableColumn}: Returns JDateTimeSpinner objects as the 'editor' for database columns that store long integers.
 </div> */

public class JLongIntegerSpinner extends JSpinner {
	public JLongIntegerSpinner() {
		super(new SpinnerNumberModel(new Long(0), new Long(0), new Long(100), new Long(1)));
	}
	
	public long getLongValue() {
		Object val = getValue();
		if (val instanceof Long)
			return ((Long)val).longValue();
		else
			return (long)((Integer)val).intValue();
	}
	
	public void setLongValue(long l) {
		setValue(new Long(l));
	}
	
	public void setMaximum(long max) {
		((SpinnerNumberModel)getModel()).setMaximum(new Long(max));
	}
	
	public void setMinimum(long min) {
		((SpinnerNumberModel)getModel()).setMinimum(new Long(min));
	}
}
