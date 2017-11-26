package com.adscoop.userservice.handlers.accountInformationHandler;

import java.util.Optional;

import com.adscoop.userservice.entites.AccountInformation;
import com.adscoop.userservice.services.AccountInformationServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 27/12/2016.
 */
public class DeleteAccountInformation implements Handler {

    AccountInformationServiceImpl accountInformationService;

   @Inject
    public DeleteAccountInformation(AccountInformationServiceImpl accountInformationService) {
        this.accountInformationService = accountInformationService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
       if(ctx.getRequest().getMethod().isDelete()){
       String token = ctx.getRequest().getHeaders().get("token");
        if(!token.isEmpty()){
       Optional<AccountInformation>  accountInformation =  accountInformationService.getByUserToken(token);
        if(accountInformation.isPresent()){
            accountInformationService.delete(accountInformation.get());

        }

        }
    }else  {
           ctx.next();
       }

   }
}

