package com.adscoop.userservice.handlers;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;

public class
CORSHandler implements Handler {

	public static final String ACCESS_CONTROL_ALLOW_HEADERS_VALUE = "x-requested-with, origin, content-type, accept";
	public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	public static final String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "*";
	public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

	@Override
	public void handle(Context ctx) throws Exception {
		MutableHeaders headers = ctx.getResponse().getHeaders();
		headers.set(ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);
		headers.set(ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS_VALUE);
		ctx.next();
	}
	
}
