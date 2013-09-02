package com.ippon.formation.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.ippon.formation.gwt.shared.LocalizedConstants;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyDataGrid implements EntryPoint {
	LocalizedConstants constants = GWT.create(LocalizedConstants.class);

	public void onModuleLoad() {
		RootLayoutPanel.get().add(new Label(constants.welcomeMsg()));
	}
}
