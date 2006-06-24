//
//  JDateTimeSpinner.java
//  Caduceus
//
//  Copyright 2005 ICP Firefly Caduceus Development Team (Z. Benitez, J. Boles, L. Constantinescu, M. Lunney).
//	All rights reserved.
//
package jb.ozepg.ui;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import java.util.Date;

/**
* A JSpinner-based control which stores and displays dates in the style: <code>E, d MMMM yyyy H:m:s</code>
 * <br><br><b>Note:</b> Millisecond components of <code>Date</code>s passed to its {@link #setValue} method are ignored.
 *
 * <div style="border: double solid black 3px; padding: 5px" class="TableHeadingColor">
 * {@link au.com.icpfirefly.client.ui.util.EnhancedSwingEngine}: Inserts this component into each SwiXML window that needs it, based on an XML UI definition. <hr>
 * {@link JDateSpinner}: A subclass which ignores time, displaying and storing only the calendar date. <hr>
 * {@link au.com.icpfirefly.client.ui.advsearch.AdvancedSearchItemPanel}: The advanced search panel, which makes common use of this class to search by date and time. <hr>
 * {@link au.com.icpfirefly.common.db.DBTableColumn}: Returns JDateTimeSpinner objects as the 'editor' for database columns that store both date and time. <hr>
 * {@link au.com.icpfirefly.client.ui.UIWindowElement}: Writes date and time to elements of this type (via {@link #setValue}).
 </div> */

public class JDateTimeSpinner extends JSpinner {
	public JDateTimeSpinner() {
		this("E, d MMMM yyyy HH:mm:ss");
		setValue(new Date());
	}
	
	public JDateTimeSpinner(String format) {
		super(new SpinnerDateModel());
		((JSpinner.DateEditor)getEditor()).getFormat().applyPattern(format);
		Calendar c = Calendar.getInstance();
		setValue(new Date());
	}
	
	public void setValue(Object o) {
		// We aren't using milliseconds, so set them to 0.
		Calendar c = Calendar.getInstance();
		c.setTime((Date)o);
		c.set(Calendar.MILLISECOND, 0);
		super.setValue(c.getTime());
	}
}
