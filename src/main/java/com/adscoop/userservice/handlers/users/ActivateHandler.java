package com.adscoop.userservice.handlers.users;

import java.util.Optional;

import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.impls.IUser;
import com.google.inject.Inject;

import ratpack.func.Action;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.TypedData;

public class ActivateHandler implements Handler {

	private IUser userService;

	@Inject
	public ActivateHandler(IUser userService) {
		this.userService = userService;
	}
	
	@Override
	public void handle(Context ctx) throws Exception {
		if (ctx.getRequest().getMethod().isPost()) {
			ctx.getRequest()
				.getBody()
				.then(verifyActivation(ctx));
		} else {
			ctx.clientError(405);
		}
	}

	protected Action<? super TypedData> verifyActivation(Context ctx) {
		return data -> {
			Optional<UserNode> optional = userService.findByUserToken(data.getText());
			if (optional.isPresent()) {
				userService.saveOrUpdate(optional.get().activated());
			} else {
				ctx.clientError(410);
			}
		};
	}

}
