package com.adscoop.userservice.handlers.auth;

import com.adscoop.com.adscoop.services.AuthorazationService;
import com.adscoop.entiites.AuthEntity;
import com.adscoop.entiites.UserNode;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.handling.Handlers;

import java.util.Optional;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 08/12/2016.
 */
public class AuthHandler implements Handler {

    AuthorazationService authorazationService;

    @Inject
    public AuthHandler(AuthorazationService authorazationService) {
        this.authorazationService = authorazationService;
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
                ctx.redirect(LoginHandler.class);
            }

        } else {
            ctx.render("missing token or password");

        }
    }

}
