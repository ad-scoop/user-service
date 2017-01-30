package com.adscoop.userservice.handlers.errorhandlers;

import ratpack.error.ClientErrorHandler;
import ratpack.error.internal.ErrorHandler;
import ratpack.handling.Context;

/**
 * Created by kleistit on 30/01/2017.
 */
public class UserServiceErrorHandler implements ClientErrorHandler {
    @Override
    public void error(Context context, int statusCode) throws Exception {
        context.render(statusCode);
    }
}
