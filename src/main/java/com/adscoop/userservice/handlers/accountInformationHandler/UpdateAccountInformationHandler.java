package com.adscoop.userservice.handlers.accountInformationHandler;


import java.util.Optional;

import com.adscoop.userservice.entites.AccountInformation;
import com.adscoop.userservice.entites.UserNode;
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
        if (ctx.getRequest().getMethod().isPut()) {
            if (ctx.getRequest().getHeaders().get("token") != null) {
                Optional<UserNode> userNode = userNodeService.findByUserToken(ctx.getRequest().getHeaders().get("token"));
                if (userNode.isPresent()) {
                    ctx.parse(AccountInformation.class).then(accountInformation -> {
                        AccountInformation accountInformation1 = new AccountInformation();

                        accountInformation1.setAccountnr(accountInformation.getAccountnr());
                        accountInformation1.setBankname(accountInformation.getBankname());
                        accountInformation1.setRegnr(accountInformation.getRegnr());


                    });

                }
            }

        } else{
            ctx.next();
        }
    }
}

