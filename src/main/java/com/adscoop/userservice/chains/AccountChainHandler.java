package com.adscoop.userservice.chains;

import com.adscoop.userservice.handlers.accountInformationHandler.CreateAccountInformationHandler;
import com.adscoop.userservice.handlers.accountInformationHandler.DeleteAccountInformation;
import com.adscoop.userservice.handlers.accountInformationHandler.GetAccountInformationHandler;
import com.adscoop.userservice.handlers.accountInformationHandler.UpdateAccountInformationHandler;
import com.adscoop.userservice.handlers.auth.AuthHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by thokle on 15/01/2017.
 */
public class AccountChainHandler implements Action<Chain>{

    @Override
    public void execute(Chain chain) throws Exception {
       chain.all(AuthHandler.class).post(CreateAccountInformationHandler.class).delete(DeleteAccountInformation.class).put(UpdateAccountInformationHandler.class).get(GetAccountInformationHandler.class);
    }
}
