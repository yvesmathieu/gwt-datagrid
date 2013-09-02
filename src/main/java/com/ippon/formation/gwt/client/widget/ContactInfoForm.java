package com.ippon.formation.gwt.client.widget;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.validation.client.impl.Validation;
import com.ippon.formation.gwt.shared.ContactDatabase;
import com.ippon.formation.gwt.shared.ContactDatabase.Category;
import com.ippon.formation.gwt.shared.ContactInfo;
import com.ippon.formation.gwt.shared.LocalizedConstants;

public class ContactInfoForm extends Composite {

	interface Binder extends UiBinder<Widget, ContactInfoForm> {
	}

	/**
	 * Validator using hibernate validation.
	 */
	Validator validator = Validation.buildDefaultValidatorFactory()
			.getValidator();

	LocalizedConstants constants = GWT.create(LocalizedConstants.class);

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

	/**
	 * Default constructor.
	 */
	ContactInfoForm() {
		Binder uiBinder = GWT.create(Binder.class);
		initWidget(uiBinder.createAndBindUi(this));

		birthDatePicker.setFormat(new DateBox.DefaultFormat(DateTimeFormat
				.getFormat(PredefinedFormat.DATE_FULL)));

		for (Category dataCategory : ContactDatabase.get().queryCategories()) {
			categoryListBox.addItem(dataCategory.getDisplayName());
		}
	}

	/**
	 * Hanlder for clicking on add button.
	 *
	 * @param event
	 */
	@UiHandler("addContactButton")
	public void handleAddContact(ClickEvent event) {
		Category category = null;

		// Retrieve category.
		for (Category dataCategory : ContactDatabase.get().queryCategories()) {
			if (dataCategory.getDisplayName().equals(
					categoryListBox.getItemText(categoryListBox
							.getSelectedIndex()))) {
				category = dataCategory;
			}
		}

		// Contact cooking.
		ContactInfo contactInfo = new ContactInfo(category);
		contactInfo.setAddress(addressTextBox.getText());
		if (firstNameTextBox.getText() != null) {
			contactInfo.setFirstName(capitalizeFirstLetter(firstNameTextBox.getText()));
		}
		if (lastNameTextBox.getText() != null) {
			contactInfo.setFirstName(capitalizeFirstLetter(lastNameTextBox.getText()));
		}
		if (birthDatePicker.getValue() != null) {
			contactInfo.setBirthday(birthDatePicker.getValue());
		}
		contactInfo.setFirstName(firstNameTextBox.getText());

		// Validation.
		Set<ConstraintViolation<ContactInfo>> cViolations = validator.validate(contactInfo);

		if (cViolations.isEmpty()) {
			// Everything is fine process.
			ContactDatabase.get().addContact(contactInfo);
			ContactDatabase.get().refreshDisplays();
		} else {
			// Ho crap ! That's why we can't have nice things.
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<ContactInfo> cv : cViolations) {
				sb.append(cv.getPropertyPath()).append(" ");
				sb.append(cv.getMessage()).append("\n");
			}

			Window.alert(sb.toString());
		}
	}

	/**
	 * Applies upper case on first letter of a string and lower case on the lasts.
	 *
	 * @param str
	 * 			the string to capitalize.
	 * @return
	 */
	private String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase()
				+ str.substring(1).toLowerCase();
	}
}
