package com.adscoop.userservice.handlers.credit;

import static ratpack.jackson.Jackson.json;

import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 10/09/2016.
 */
public class GetCreditHandler implements Handler {

    private UserNodeServiceImpl userNodeService;

@Inject
    public GetCreditHandler(UserNodeServiceImpl userNodeService) {
        this.userNodeService = userNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Long id = Long.valueOf(ctx.getRequest().getHeaders().get("userid"));
        UserNode userNode  = userNodeService.findbyId(id);
        ctx.render(json(userNode.getCreditInfos()));

    }
}
