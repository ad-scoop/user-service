package com.adscoop.userservice.handlers.auth;


import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adscoop.userservice.congfig.UserModel;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.impls.IAuthorazationService;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 12/12/2016.
 */
public class LoginHandler implements Handler {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginHandler.class);

    private IAuthorazationService authorazationService;

    @Inject
    public LoginHandler(IAuthorazationService authorazationService) {

        this.authorazationService = authorazationService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.parse(fromJson(UserModel.class)).then(userModel -> {
        	LOGGER.debug("Logging in with " + userModel.getEmail());
            Optional<UserNode> st = authorazationService.login(userModel.getEmail(), userModel.getPassword());
            if (st.isPresent()) {
                ctx.getResponse().getHeaders().add("usertoken", st.get().getToken());
                ctx.render(json(st.get()));
            } else {
            	LOGGER.debug("Invalid username or password for " + userModel.getEmail());
                ctx.render("Invalid username or password");
            }
        });
    }
}
