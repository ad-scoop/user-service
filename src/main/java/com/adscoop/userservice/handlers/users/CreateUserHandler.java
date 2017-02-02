package com.adscoop.userservice.handlers.users;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import com.adscoop.userservice.congfig.TokenService;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;


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


        if (ctx.getRequest().getMethod().isPost()) {
            ctx.parse(fromJson(UserNode.class)).then(as -> {
                if (userNodeService.doesUserExist(as.getEmail())) {
                    ctx.getResponse().send("application/json", "{\"exist\":\"user with email already exist \"}");
                }
                final UserNode userNode = new UserNode();

                userNode.setFirstname(as.getFirstname());
                userNode.setLastname(as.getLastname());
                userNode.setToken(generateToken.generateToken());
                userNode.setMiddlename(as.getMiddlename());
                userNode.setUsername(as.getUsername());
                userNode.setPassword(as.getPassword());
                userNode.setEmail(as.getEmail());
                as.getLabels().stream().forEach(la -> {
                    userNode.getLabels().add(la);
                });

                UserNode saved = userNodeService.saveOrUpdate(userNode);
                ctx.getResponse().getHeaders().add("token", saved.getToken());
                ctx.render(json(userNode, UserNode.class));

            });

        }
ctx.next();
    }




}
