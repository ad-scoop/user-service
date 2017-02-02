package com.adscoop.userservice.handlers.accountInformationHandler;

import static ratpack.jackson.Jackson.json;

import java.util.Optional;

import com.adscoop.userservice.entites.UserNode;
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
        if (ctx.getRequest().getMethod().isGet()) {
            String id = ctx.getRequest().getHeaders().get("token");
            if (id != null) {
                Optional<UserNode> userNode = userNodeService.findByUserToken(id);
                if (userNode.isPresent()) {
                    ctx.render(json(userNode.get().getAccountInformations()));
                }
            }

        } else {
            ctx.next();
        }
    }
}
