package com.ippon.formation.gwt.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.ippon.formation.gwt.client.widget.ContactInfoForm;
import com.ippon.formation.gwt.client.widget.MySampleDataGrid;

public class FirstScreen extends Composite {

	interface Binder extends UiBinder<Widget, FirstScreen> {};
	private Binder uiBinder = GWT.create(Binder.class); 
	
	
	@UiField
	MySampleDataGrid datagrid;
	
	@UiField
	ContactInfoForm contactForm;
	
	public FirstScreen() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
