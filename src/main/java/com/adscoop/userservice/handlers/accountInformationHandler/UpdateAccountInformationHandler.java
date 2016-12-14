package com.adscoop.userservice.handlers.accountInformationHandler;

import com.adscoop.entiites.AccountInformation;
import com.adscoop.entiites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 22/11/2016.
 */
public class UpdateAccountInformationHandler implements Handler {

    private UserNodeServiceImpl userNodeService;


    @Inject
    public UpdateAccountInformationHandler(UserNodeServiceImpl userNodeService) {
        this.userNodeService = userNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        if(ctx.getRequest().getHeaders().get("token") !=null){
            UserNode  userNode =  userNodeService.findByToken(ctx.getRequest().getHeaders().get("token"));
            if(userNode !=null){
                ctx.parse(AccountInformation.class).then(accountInformation ->  {
                    AccountInformation accountInformation1 = new AccountInformation();

                    accountInformation1.setAccountnr(accountInformation.getAccountnr());
                    accountInformation1.setBankname(accountInformation.getBankname());
                    accountInformation1.setRegnr(accountInformation.getRegnr());



                });

            }
        }

    }
}

