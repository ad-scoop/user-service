package com.adscoop.userservice.handlers.credit;

import com.adscoop.entiites.UserNode;
import com.google.inject.Inject;

import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

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
