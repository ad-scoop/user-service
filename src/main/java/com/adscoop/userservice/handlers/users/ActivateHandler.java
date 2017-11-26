package com.adscoop.userservice.handlers.users;

import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.IUser;
import com.google.inject.Inject;

import ratpack.exec.Promise;
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
			Promise<UserNode> optional = userService.findByUserToken(data.getText());
			optional.then( userNode -> {
				if (userNode.getEmail() !=null) {
					userService.saveOrUpdate(userNode);
					ctx.render("User " + userNode.getFirstname() + "  "+userNode.getLastname() + " isActivated");
				} else {
					ctx.clientError(410);
				}


			});

		};
	}

}
