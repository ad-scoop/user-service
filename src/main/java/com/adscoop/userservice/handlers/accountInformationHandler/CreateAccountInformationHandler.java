package com.adscoop.userservice.handlers.accountInformationHandler;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import com.adscoop.userservice.entites.AccountInformation;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 07/11/2016.
 */
public class CreateAccountInformationHandler implements Handler {

    private UserNodeServiceImpl userNodeService;

    @Inject
    public CreateAccountInformationHandler(UserNodeServiceImpl userNodeService) {
        this.userNodeService = userNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Long id = Long.valueOf(ctx.getRequest().getHeaders().get("userid"));
if(ctx.getRequest().getMethod().isPost()) {
    if (id != null) {
        ctx.parse(fromJson(AccountInformation.class)).then(accountInformation -> {
            UserNode userNode = userNodeService.findbyId(id);

            AccountInformation accountInformation1 = new AccountInformation();
            accountInformation1.setAccountnr(accountInformation.getAccountnr());
            accountInformation1.setBankname(accountInformation.getBankname());
            accountInformation1.setRegnr(accountInformation.getRegnr());


            userNode.setAccountInformation(accountInformation1);
            userNodeService.saveOrUpdate(userNode);

            ctx.render(json(accountInformation1));


        });


    }
} ctx.next();

    }
}
