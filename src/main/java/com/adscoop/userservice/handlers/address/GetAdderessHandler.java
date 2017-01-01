package com.adscoop.userservice.handlers.address;


import com.adscoop.entiites.AddressNode;
import com.google.inject.Inject;

import com.adscoop.userservice.services.impls.AddressUserServiceImpl;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Optional;

import static ratpack.jackson.Jackson.json;

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
