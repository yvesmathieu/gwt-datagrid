package com.ippon.formation.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyDataGrid implements EntryPoint {

	public void onModuleLoad() {
		FirstScreen firstScreen = new FirstScreen();
		RootLayoutPanel.get().add(firstScreen);
	}
}
