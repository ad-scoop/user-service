package com.adscoop.userservice.handlers.accountInformationHandler;

import static ratpack.jackson.Jackson.json;

import com.adscoop.entiites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 13/11/2016.
 */
public class GetAccountInformationHandler implements Handler {

    private UserNodeServiceImpl userNodeService;


    @Inject
    public GetAccountInformationHandler(UserNodeServiceImpl userNodeService) {
        this.userNodeService = userNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Long id = Long.valueOf(ctx.getRequest().getHeaders().get("userid"));
        if(id !=null){
            UserNode userNode = userNodeService.findbyId(id);
            ctx.render(json(userNode.getAccountInformations()));

        }

    }
}
