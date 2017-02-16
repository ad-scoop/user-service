package com.adscoop.userservice.exceptions;

import ratpack.error.ClientErrorHandler;
import ratpack.handling.Context;

/**
 * Created by thokle on 16/02/2017.
 */
public class UserServiceClientErrorHandler implements ClientErrorHandler {
    @Override
    public void error(Context context, int statusCode) throws Exception {
       context.render(context.file("static/404.html"));
    }
}
