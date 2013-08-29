package com.ippon.formation.gwt.client.widget;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.ippon.formation.gwt.shared.ContactDatabase;
import com.ippon.formation.gwt.shared.ContactDatabase.Category;
import com.ippon.formation.gwt.shared.ContactInfo;


public class ContactInfoForm extends Composite {

	interface Binder extends UiBinder<HTMLPanel, ContactInfoForm> {
	}
	
	@UiField
	Label firstNameLabel;
	@UiField
	Label lastNameLabel;
	@UiField
	Label addressLabel;
	@UiField
	Label birthDateLabel;
	@UiField
	Label categoryLabel;
	
	@UiField
	TextBox firstNameTextBox;
	@UiField
	TextBox lastNameTextBox;
	@UiField
	TextBox addressTextBox;
	@UiField
	DateBox birthDatePicker;
	@UiField
	ListBox categoryListBox;
	@UiField
	Button addContactButton;
	
	ContactInfoForm() {
		Binder uiBinder = GWT.create(Binder.class);
		initWidget(uiBinder.createAndBindUi(this));
		
		for (Category dataCategory : ContactDatabase.get().queryCategories()) {
			categoryListBox.addItem(dataCategory.getDisplayName());
		}
	}
	
	private boolean validForm() {
		boolean valid = true;
		
//		if (firstNameLabel.getText()) {
//			
//		}
		
		return valid;
	}
	
	@UiHandler("addContactButton")
	public void handleAddContact(ClickEvent event) {
		if (validForm()) {
			Category category = null;
			
			for (Category dataCategory : ContactDatabase.get().queryCategories()) {
				if (dataCategory.getDisplayName().equals(categoryListBox.getItemText(categoryListBox.getSelectedIndex()))) {
					category = dataCategory;
				}
			}
			
			ContactInfo contactInfo = new ContactInfo(category);
			contactInfo.setAddress(addressTextBox.getText());
			contactInfo.setFirstName(firstNameTextBox.getText());
			contactInfo.setLastName(lastNameTextBox.getText());
			contactInfo.setBirthday(birthDatePicker.getValue());
			contactInfo.setFirstName(firstNameTextBox.getText());
			
			ContactDatabase.get().addContact(contactInfo);
			ContactDatabase.get().refreshDisplays();
		}
	}
}
