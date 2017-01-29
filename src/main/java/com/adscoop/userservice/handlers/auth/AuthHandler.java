package com.adscoop.userservice.handlers.auth;


import com.adscoop.com.adscoop.services.AuthorazationService;

import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;


import java.util.Optional;

;

/**
 * Created by thokle on 08/12/2016.
 */
public class AuthHandler implements Handler {


    private AuthorazationService authorazationService;
    @Inject
    public AuthHandler(AuthorazationService authorazationServiceau) {
        this.authorazationService = authorazationServiceau;
    }

    @Override
    public void handle(Context ctx) throws Exception {


        String token = ctx.getRequest().getHeaders().get("token");
        String username = ctx.getRequest().getHeaders().get("username");

         if ((token != null) && (username != null)) {

            Optional<String> res = authorazationService.tokenExist(username, token);
            if (res.isPresent()) {
                ctx.next();
            } else {

            }

        } else {
            ctx.render("missing token or password");

        }
    }

}
