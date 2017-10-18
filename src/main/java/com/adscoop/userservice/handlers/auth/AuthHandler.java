package com.adscoop.userservice.handlers.auth;


import java.util.Optional;

import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.IAuthorazationService;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

;

/**
 * Created by thokle on 08/12/2016.
 */
public class AuthHandler implements Handler {


    private IAuthorazationService authorazationService;
    @Inject
    public AuthHandler(IAuthorazationService authorazationServiceau) {
        this.authorazationService = authorazationServiceau;
    }

    @Override
    public void handle(Context ctx) throws Exception {


        String token = ctx.getRequest().getHeaders().get("token");


         if (token != null) {
            try {
                Optional<UserNode> res = authorazationService.tokenExist(token);
                if(res.isPresent()){
                    ctx.next();
                } else {
                    ctx.getResponse().status(405).send("No Access");
                }

            } catch (Exception e){
                ctx.getResponse().status(405).send("No user with token exist");
            }

        } else {
            ctx.render("missing token or password");

        }
    }

}
