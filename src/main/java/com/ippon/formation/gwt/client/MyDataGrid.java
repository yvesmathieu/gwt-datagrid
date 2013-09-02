package com.ippon.formation.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.ippon.formation.gwt.client.widget.MySampleDataGrid;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyDataGrid implements EntryPoint {

	public void onModuleLoad() {
		MySampleDataGrid datagrid = new MySampleDataGrid();
		RootLayoutPanel.get().add(datagrid);
	}
}
