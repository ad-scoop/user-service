package com.adscoop.userservice.handlers.users;


import com.adscoop.userservice.services.UserNodeServiceImpl;
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
if(ctx.getRequest().getMethod().isDelete()){
        String token = ctx.getRequest().getHeaders().get("token");

         userNodeService.findByUserToken(token).then(userNode -> {
             userNodeService.delete(userNode);
         } );

    }
    ctx.next();
    }

}
