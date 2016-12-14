package com.adscoop.userservice.handlers.users;

import com.adscoop.entiites.UserNode;
import com.google.inject.Inject;


import com.adscoop.userservice.congfig.TokenService;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;


/**
 * Created by thokle on 10/09/2016.
 */
public class CreateUserHandler implements Handler {

    private UserNodeServiceImpl userNodeService;
  private TokenService generateToken;
    @Inject
    public CreateUserHandler(UserNodeServiceImpl userNodeService, TokenService generateToken) {
        this.userNodeService = userNodeService;
        this.generateToken = generateToken;
    }




    @Override
    public void handle(Context ctx) throws Exception {

        ctx.parse(fromJson(UserNode.class)).then(as -> {

            final UserNode userNode  = new UserNode();

            userNode.setFirstname(as.getFirstname());
            userNode.setLastname(as.getLastname());
            userNode.setToken(generateToken.generateToken());
            userNode.setMiddlename(as.getMiddlename());
            userNode.setUsername(as.getUsername());
            userNode.setPassword(as.getPassword());
            userNode.setEmail(as.getEmail());
            as.getLabels().stream().forEach( la -> {
                userNode.getLabels().add(la);
            });

            UserNode saved =    userNodeService.saveOrUpdate(userNode);
            ctx.getResponse().getHeaders().add("userid",saved.getId()).add("token",saved.getToken());
            ctx.render(json(saved, UserNode.class));

        });



    }
}
