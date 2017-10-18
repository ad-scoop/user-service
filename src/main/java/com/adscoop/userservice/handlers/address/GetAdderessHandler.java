package com.adscoop.userservice.handlers.address;


import static ratpack.jackson.Jackson.json;

import java.util.Optional;

import com.adscoop.userservice.entites.AddressNode;
import com.adscoop.userservice.services.AddressUserServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 10/09/2016.
 */
public class GetAdderessHandler implements Handler {


    private AddressUserServiceImpl addressUserService;

    @Inject
    public GetAdderessHandler(AddressUserServiceImpl addressUserService) {
        this.addressUserService = addressUserService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String cypher = ctx.getRequest().getHeaders().get("userid");
         Optional<AddressNode> addressNode =  addressUserService.findByUser(cypher);
        ctx.render(json(addressNode.get()));
    }
}
