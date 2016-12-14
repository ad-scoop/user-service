package com.adscoop.userservice.congfig;

import org.apache.commons.validator.routines.CreditCardValidator;

/**
 * Created by thokle on 13/11/2016.
 */
public class CreditCardValidatorUtil {



    public boolean validateCart(String number){

        CreditCardValidator creditCardValidator = new CreditCardValidator(CreditCardValidator.VISA);


        return creditCardValidator.isValid(number);



    }
}
