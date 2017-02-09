package com.adscoop.userservice.handlers.users;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import com.adscoop.userservice.congfig.TokenService;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.impls.IUser;
import com.google.inject.Inject;

import ratpack.func.Action;
import ratpack.handling.Context;
import ratpack.handling.Handler;

public class ActivateHandler implements Handler {

	private IUser userService;

	@Inject
	public ActivateHandler(IUser userService) {
		this.userService = userService;
	}
	
	@Override
	public void handle(Context ctx) throws Exception {
		if (ctx.getRequest().getMethod().isPost()) {
			ctx
				.parse(String.class)
				.then(verifyActivation(ctx));
		} else {
			ctx.clientError(405);
		}
	}

	protected Action<? super String> verifyActivation(Context ctx) {
		return activation  -> {
			Optional<UserNode> optional = userService.findByUserToken(activation);
			if (optional.isPresent()) {
				userService.saveOrUpdate(optional.get().activated());
			} else {
				ctx.clientError(410);
			}
		};
	}

}
