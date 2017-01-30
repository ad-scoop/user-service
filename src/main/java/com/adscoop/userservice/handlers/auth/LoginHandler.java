package com.adscoop.userservice.handlers.auth;


import static ratpack.jackson.Jackson.fromJson;

import java.util.Optional;

import com.adscoop.userservice.congfig.UserModel;
import com.adscoop.userservice.modules.AuthorazationService;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 12/12/2016.
 */
public class LoginHandler implements Handler {

    private AuthorazationService authorazationService;

    @Inject
    public LoginHandler(AuthorazationService authorazationService) {

        this.authorazationService = authorazationService;
    }

    @Override
    public void handle(Context ctx) throws Exception {


        ctx.parse(fromJson(UserModel.class)).then(userModel -> {

            Optional<String> st = authorazationService.login(userModel.getUsername(), userModel.getPassword());

            if (st.isPresent()) {
                ctx.getResponse().getHeaders().add("user-token", st.get());
                ctx.render(st.get());

            } else {
                ctx.render("not working");

            }


        });


    }
}
