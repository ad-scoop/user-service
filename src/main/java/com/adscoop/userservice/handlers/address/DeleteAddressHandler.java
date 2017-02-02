package com.adscoop.userservice.handlers.address;

import com.adscoop.userservice.services.impls.AddressUserServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 23/12/2016.
 */
public class DeleteAddressHandler implements Handler {

    private AddressUserServiceImpl addressUserService;

@Inject
public DeleteAddressHandler(AddressUserServiceImpl addressUserService) {
        this.addressUserService = addressUserService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        if(ctx.getRequest().getMethod().isDelete()){

        }else{
            ctx.next();
        }
    }
}
