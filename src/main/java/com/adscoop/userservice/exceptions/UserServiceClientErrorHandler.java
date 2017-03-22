package com.adscoop.userservice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ratpack.error.ClientErrorHandler;
import ratpack.handling.Context;

/**
 * Created by thokle on 16/02/2017.
 */
public class UserServiceClientErrorHandler implements ClientErrorHandler {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceClientErrorHandler.class);

	@Override
	public void error(Context context, int statusCode) throws Exception {
		LOGGER.error("Client error with status code: " + statusCode);
		context.render(context.file("static/404.html"));
	}
	
}
