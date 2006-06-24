//
//  JDateField.java
//  Caduceus
//
//  Copyright 2005 ICP Firefly Caduceus Development Team (Z. Benitez, J. Boles, L. Constantinescu, M. Lunney).
//	All rights reserved.
//
package jb.ozepg.ui;
import java.util.Date;
import java.util.Calendar;

/**
* A subclass of the {@link JDateTimeSpinner} which displays only a date, and not a time.
 * Time components of <code>Date</code>s passed to its {@link #setValue} method are ignored.
 *
 * <div style="border: double solid black 3px; padding: 5px" class="TableHeadingColor">
 * {@link au.com.icpfirefly.client.ui.util.EnhancedSwingEngine}: Inserts this component into each SwiXML window that needs it, based on an XML UI definition. <hr>
 * {@link JDateTimeSpinner}: The (complete) superclass. <hr>
 * {@link au.com.icpfirefly.client.ui.advsearch.AdvancedSearchItemPanel}: The advanced search panel, which makes common use of this class to search by date. <hr>
 * {@link au.com.icpfirefly.common.db.DBTableColumn}: Returns JDateSpinner objects as the 'editor' for database columns that store dates. <hr>
 * {@link au.com.icpfirefly.client.ui.UIWindowElement}: Writes dates to elements of this type (via {@link #setValue}).
 </div> */

public class JDateSpinner extends JDateTimeSpinner {
	public JDateSpinner() {
		super("E, d MMMM yyyy");
	}
	
	public void setValue(Object o) {
		// We are only setting a date, so set the time to 0.
		Calendar c = Calendar.getInstance();
		c.setTime((Date)o);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		super.setValue(c.getTime());
	}
}
