//
//  EnhancedSwingEngine.java
//  Caduceus
//
//  Copyright 2005 ICP Firefly Caduceus Development Team (Z. Benitez, J. Boles, L. Constantinescu, M. Lunney).
//	All rights reserved.
//
package jb.ozepg.ui;
import org.swixml.SwingEngine;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

// The below can be generated with the following regex find/replace if it changes:
// Regex find: \t\t\tthis\.getTaglib\(\)\.registerTag\("(.*)", (.*)\.class\);
// Regex replace:  * <li><code><b>\&lt;\1\&gt;</b></code>: A SwiXML control tag that inserts the {@link \2} class.</li>

/**
* This class is an extension to the SwiXML SwingEngine class which adds new SwiXML tags for custom objects.
 * It adds support for the following new tags:
 *
 * <ul><li><code><b>&lt;searchfield&gt;</b></code>: A SwiXML control tag that inserts the {@link JSearchField} class.</li>
 * <li><code><b>&lt;smarttextfield&gt;</b></code>: A SwiXML control tag that inserts the {@link SmartTextField} class.</li>
 * <li><code><b>&lt;smartcombobox&gt;</b></code>: A SwiXML control tag that inserts the {@link SmartComboBox} class.</li>
 * <li><code><b>&lt;browsertable&gt;</b></code>: A SwiXML control tag that inserts the {@link BrowserTable} class.</li>
 * <li><code><b>&lt;alerttable&gt;</b></code>: A SwiXML control tag that inserts the {@link AlertTable} class.</li>
 * <li><code><b>&lt;longintegerfield&gt;</b></code>: A SwiXML control tag that inserts the {@link JLongIntegerField} class.</li>
 * <li><code><b>&lt;datespinner&gt;</b></code>: A SwiXML control tag that inserts the {@link JDateSpinner} class.</li>
 * <li><code><b>&lt;datetimespinner&gt;</b></code>: A SwiXML control tag that inserts the {@link JDateTimeSpinner} class.</li>
 * <li><code><b>&lt;longintegerspinner&gt;</b></code>: A SwiXML control tag that inserts the {@link JLongIntegerSpinner} class.</li>
 * <li><code><b>&lt;enhancedframe&gt;</b></code>: A SwiXML control tag that inserts the {@link EnhancedJFrame} class.</li>
 * <li><code><b>&lt;advancedsearchconditionspanel&gt;</b></code>: A SwiXML control tag that inserts the {@link AdvancedSearchConditionsPanel} class.</li>
 * <li><code><b>&lt;filefield&gt;</b></code>: A SwiXML control tag that inserts the {@link FileField} class.</li></ul>
 * 
 * <div style="border: double solid black 3px; padding: 5px" class="TableHeadingColor">
 * {@link org.swixml.SwingEngine}: The base class. <hr>
 * {@link au.com.icpfirefly.client.ui.advsearch.AdvancedSearchResultsWindow}: A class whose UI is rendered by this engine. <hr>
 * {@link au.com.icpfirefly.client.ui.advsearch.AdvancedSearchWindow}: A class whose UI is rendered by this engine. <hr>
 * {@link EnhancedJFrame}: A class whose UI is rendered by this engine. <hr>
 * {@link EnhancedSwingEngine}: A class whose UI is rendered by this engine. <hr>
 * {@link au.com.icpfirefly.common.datatypes.GenericWindowType}: A class whose UI is rendered by this engine. <hr>
 * {@link au.com.icpfirefly.client.ui.alerts.AlertInterface}: A class whose UI is rendered by this engine. <hr>
 * {@link NotesWindow}: A class whose UI is rendered by this engine. <hr>
 * {@link UI}: A class whose UI is rendered by this engine. <hr>
 * {@link UIWindowElement}: A class whose UI is rendered by this engine. <hr>
 * {@link UIWindowUI}: A class whose UI is rendered by this engine. <hr>
 * {@link au.com.icpfirefly.client.ui.workflow.WorkflowDesignerButtonPopup}: A class whose UI is rendered by this engine.
 </div> */

public class EnhancedSwingEngine extends SwingEngine {
	Map components;
	
	public EnhancedSwingEngine(Object o) {
		super(o);
		
		try {
			this.getTaglib().registerTag("datespinner", JDateSpinner.class);
			this.getTaglib().registerTag("datetimespinner", JDateTimeSpinner.class);
			this.getTaglib().registerTag("longintegerspinner", JLongIntegerSpinner.class);
		} catch (Exception e) {
			System.err.println("Couldn't register a new tag.");
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
	
	public Component find(String s) {
		if (components != null) {
			return (Component)components.get(s);
		} else {
			return super.find(s);
		}
	}
	
	public Map getComponentMap() {
		if (components == null) components = new HashMap();
		return components;
	}
}
