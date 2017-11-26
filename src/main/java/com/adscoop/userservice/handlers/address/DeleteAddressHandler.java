package com.adscoop.userservice.handlers.address;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 23/12/2016.
 */
public class DeleteAddressHandler implements Handler {

	@Override
	public void handle(Context ctx) throws Exception {
		if (ctx.getRequest().getMethod().isDelete()) {

		} else {
			ctx.next();
		}
	}
}
