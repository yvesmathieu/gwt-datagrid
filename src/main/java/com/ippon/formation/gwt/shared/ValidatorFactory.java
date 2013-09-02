package com.ippon.formation.gwt.shared;

import javax.validation.Validator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

/**
 * Factory for validators, allow use of hibernate validation.
 *
 * @author ymrideau
 *
 */
public final class ValidatorFactory extends AbstractGwtValidatorFactory {

	/**
	 * Validator marker for the Validation Sample project. Only the classes
	 * listed in the {@link GwtValidation} annotation can be validated.
	 */
	@GwtValidation(value = ContactInfo.class)
	public interface GwtValidator extends Validator {
	}

	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(GwtValidator.class);
	}
}
