package com.adscoop.userservice.handlers.users;

import com.adscoop.userservice.services.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;

public class GetUserHandler implements Handler {

	private UserNodeServiceImpl userNodeService;

	@Inject
	public GetUserHandler(UserNodeServiceImpl userNodeService) {
		this.userNodeService = userNodeService;
	}

	@Override
	public void handle(Context ctx) throws Exception {
		if (ctx.getRequest().getMethod().isGet()) {
			String path = ctx.getRequest().getHeaders().get("token");
			if (!path.isEmpty()) {
				RxRatpack.observe(userNodeService.findByUserToken(path)).toList().forEach( userNodes -> {

					ctx.render(userNodes);

				});


			}
		}
	}

}
