package com.adscoop.userservice.handlers.users;

import static ratpack.jackson.Jackson.json;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.adscoop.entiites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import ratpack.handling.Context;
import ratpack.handling.Handler;


public class GetUserHandler implements Handler {


    private UserNodeServiceImpl userNodeService;


    @Inject
    public GetUserHandler(UserNodeServiceImpl userNodeService) {
        this.userNodeService = userNodeService;


    }

    @Override
    public void handle(Context ctx) throws Exception {


        if (ctx.getRequest().getMethod().isGet()) {
            String path = ctx.getRequest().getHeaders().get("token");
            if (!path.isEmpty()) {
                Optional<UserNode> node = userNodeService.findByUserToken(path);
                if (node.isPresent()) {
                    ctx.render(json(node.get()));
                }

            }
        }

    }


}


