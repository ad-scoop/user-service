package com.adscoop.userservice.exceptions;

import ratpack.error.ServerErrorHandler;
import ratpack.handling.Context;

/**
 * Created by thokle on 06/02/2017.
 */
public class UserServiceClientExceptionHandler implements ServerErrorHandler {
    @Override
    public void error(Context context, Throwable throwable) throws Exception {
        context.getResponse().status(500);
        context.render(context.file("files/500.html"));
        throwable.printStackTrace();
    }
    
}
