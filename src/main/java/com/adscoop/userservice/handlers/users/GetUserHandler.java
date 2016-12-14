package com.adscoop.userservice.handlers.users;

import com.adscoop.entiites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;


import io.netty.buffer.ByteBufAllocator;

import ratpack.handling.Context;
import ratpack.handling.Handler;


import java.util.Collection;
import java.util.stream.Collectors;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;


public class GetUserHandler implements Handler {


    private UserNodeServiceImpl userNodeService;



    private ByteBufAllocator byteBufAllocator;
    private ObjectMapper objectMapper;

    @Inject
    public GetUserHandler(UserNodeServiceImpl userNodeService) {
        this.userNodeService = userNodeService;


    }

    @Override
    public void handle(Context ctx) throws Exception {
        this.byteBufAllocator = ctx.get(ByteBufAllocator.class);
        objectMapper = ctx.get(ObjectMapper.class);

        String path = ctx.getPathTokens().toString();

        if (path.equals("id")) {
            UserNode userNode = userNodeService.findbyId(new Long(path));
            ctx.render(json(userNode));

        } else if (path.equals("cypher")) {

            ctx.render(userNodeService.findByCypher(path));
        } else {


         Collection<UserNode> userNodes = userNodeService.findAll();

            ctx.render(json(userNodes.stream().collect(Collectors.toList())));



        }


    }

}


