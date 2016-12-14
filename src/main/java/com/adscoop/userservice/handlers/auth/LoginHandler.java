package com.adscoop.userservice.handlers.auth;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 12/12/2016.
 */
public class LoginHandler  implements Handler{


    @Override
    public void handle(Context ctx) throws Exception {
        ctx.getResponse().send("application/json","invalid token");
    }
}
