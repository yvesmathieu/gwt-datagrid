package com.ippon.formation.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyDataGrid implements EntryPoint {

	public void onModuleLoad() {
		MySampleDataGrid dataGrid = new MySampleDataGrid();
		RootPanel.get().add(dataGrid.asWidget());
	}
}
