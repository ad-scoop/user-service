package com.adscoop.userservice.congfig;

import org.apache.commons.validator.routines.CreditCardValidator;

/**
 * Created by thokle on 13/11/2016.
 */

public class CreditCardValidatorUtil {

	public CreditCardValidator validateCart(String number, CardType cardType) {
		switch (cardType) {
		case MASTERCARD:
			return new CreditCardValidator(CreditCardValidator.MASTERCARD);
		case DANKORT:
			return new CreditCardValidator(CreditCardValidator.VISA);
		default:
			return new CreditCardValidator(CreditCardValidator.NONE);
		}
	}
}
