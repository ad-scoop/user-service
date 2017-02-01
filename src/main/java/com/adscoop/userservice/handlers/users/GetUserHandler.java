package com.adscoop.userservice.handlers.users;

import static ratpack.jackson.Jackson.json;

import java.util.Collection;
import java.util.stream.Collectors;

import com.adscoop.entiites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class GetUserHandler implements Handler {

	private UserNodeServiceImpl userNodeService;

	@Inject
	public GetUserHandler(UserNodeServiceImpl userNodeService) {
		this.userNodeService = userNodeService;
	}

	@Override
	public void handle(Context ctx) throws Exception {
		if (ctx.getRequest().getMethod().isGet()) {
			String path = ctx.getPathTokens().toString();
			if (path.equals("id")) {
				UserNode userNode = userNodeService.findbyId(new Long(path));
				ctx.render(json(userNode));
			} else if (path.equals("cypher")) {
				ctx.render(userNodeService.findByCypher(path));
			} else {
				Collection<UserNode> userNodes = userNodeService.findAll();
				ctx.render(json(userNodes.stream().collect(Collectors.toList())));
			}
		} else {
			ctx.next();
		}

	}

}
