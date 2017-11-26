package com.adscoop.userservice.handlers.users;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import com.adscoop.userservice.congfig.AEService;
import com.adscoop.userservice.congfig.TokenService;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class CreateUserHandler implements Handler {

    private UserNodeServiceImpl userNodeService;
    private TokenService generateToken;
  	private AEService aeService;
    
    @Inject
    public CreateUserHandler(UserNodeServiceImpl userNodeService, TokenService generateToken,AEService aeService) {
        this.userNodeService = userNodeService;
        this.generateToken = generateToken;
        this.aeService = aeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        if (ctx.getRequest().getMethod().isPost()) {
            ctx.parse(fromJson(UserNode.class)).then(as -> {
                if (userNodeService.userNotExistByEmail(as.getEmail())) {
                    ctx.clientError(409);
                } else {
	                UserNode saved = userNodeService.saveOrUpdate(mapUserModel(as));
	                ctx.getResponse().getHeaders().add("token", saved.getToken());
	                ctx.render(json(saved, UserNode.class));
                }

            });
        }
    }

	private UserNode mapUserModel(UserNode as) {
		return  UserNode.builder().
                isActivated(false).
                firstname(as.getFirstname()).
                lastname(as.getLastname()).
                token(generateToken.generateToken()).
                middlename(as.getMiddlename()).
                username(as.getUsername()).
                password(aeService.encrypt(as.getPassword())).
                email(as.getEmail()).labels(as.getLabels()).build();

	}

}
