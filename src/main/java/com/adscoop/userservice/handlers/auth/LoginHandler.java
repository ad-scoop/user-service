package com.adscoop.userservice.handlers.auth;

import com.adscoop.entiites.UserNode;
import com.adscoop.userservice.congfig.UserModel;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Optional;

import static ratpack.jackson.Jackson.fromJson;

/**
 * Created by thokle on 12/12/2016.
 */
public class LoginHandler  implements Handler{

    UserNodeServiceImpl userNodeService;

@Inject
    public LoginHandler(UserNodeServiceImpl userNodeService) {
        this.userNodeService = userNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {


        ctx.parse(fromJson(UserModel.class)).then( userModel ->  {
          Optional<UserNode> userNode =  userNodeService.findByUserNameAndPassword(userModel.getUsername(),userModel.getPassword());

            if(userNode.isPresent()) {
                ctx.getResponse().getHeaders().set("token", userNode.get().getToken());
            }else{
                ctx.getResponse().send("application/text", "username or password invalid");
            }
        });
        ctx.getResponse().send("application/json","invalid token");

    }
}
