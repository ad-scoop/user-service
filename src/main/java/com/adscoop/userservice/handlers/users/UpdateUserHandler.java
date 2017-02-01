package com.adscoop.userservice.handlers.users;

import com.adscoop.entiites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import ratpack.form.Form;
import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 10/09/2016.
 */
public class UpdateUserHandler implements Handler {

    private UserNodeServiceImpl userNodeService;

    @Inject
    public UpdateUserHandler(UserNodeServiceImpl userNodeService) {
        this.userNodeService = userNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {

if(ctx.getRequest().getMethod().isPut()){
            ctx.parse(Form.class).then(as -> {
                ObjectMapper objectMapper = ctx.get(ObjectMapper.class);


                Long id = Long.valueOf(ctx.getRequest().getHeaders().get("userid"));

                UserNode userNode = userNodeService.findbyId(id);
                userNode.setFirstname(as.get("first_name"));
                userNode.setLastname(as.get("last_name"));
                userNode.setEmail(as.get("email"));
                userNode.setPassword(as.get("password"));

                userNodeService.saveOrUpdate(userNode);
            });




    }
    ctx.next();
    }

}
