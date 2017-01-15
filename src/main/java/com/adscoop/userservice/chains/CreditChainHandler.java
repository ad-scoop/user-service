package com.adscoop.userservice.chains;

import com.adscoop.userservice.handlers.auth.AuthHandler;
import com.adscoop.userservice.handlers.credit.CreateCreditHandler;
import com.adscoop.userservice.handlers.credit.DeleteCreditCardHandler;
import com.adscoop.userservice.handlers.credit.GetCreditHandler;
import com.adscoop.userservice.handlers.credit.UpdateCreditHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by thokle on 15/01/2017.
 */
public class CreditChainHandler implements Action<Chain> {

@Override
public void execute(Chain cre) throws Exception{

     cre.all(AuthHandler.class).post(CreateCreditHandler.class).delete(DeleteCreditCardHandler.class).put(UpdateCreditHandler.class).get(GetCreditHandler.class);


}

}
