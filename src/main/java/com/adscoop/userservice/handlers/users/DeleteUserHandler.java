package com.adscoop.userservice.handlers.users;

import com.adscoop.entiites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 25/12/2016.
 */
public class DeleteUserHandler implements Handler
{

    private UserNodeServiceImpl userNodeService;


    @Inject
    public DeleteUserHandler(UserNodeServiceImpl userNodeService) {
        this.userNodeService = userNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");

        UserNode userNode = userNodeService.findByToken(token);

        if(userNode !=null){

            try {
                userNodeService.delete(userNode);
            } catch (Exception e){

            }
        }

    }
}
