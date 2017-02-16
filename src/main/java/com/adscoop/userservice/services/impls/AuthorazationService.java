package com.adscoop.userservice.services.impls;

import java.util.Collections;
import java.util.Optional;

import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adscoop.userservice.congfig.AEService;
import com.adscoop.userservice.entites.UserNode;
import com.google.inject.Inject;

/**
 * Created by thokle on 04/12/2016.
 */

public class AuthorazationService implements IAuthorazationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorazationService.class);

	private Session session;
	private AEService aeService;

	@Inject
	public AuthorazationService(Session session, AEService aeService) {
		this.session = session;
		this.aeService = aeService;
	}

	@Override
	public Optional<UserNode> login(String username, String password) {
		Optional<UserNode> userNode = Optional.ofNullable(session.queryForObject(UserNode.class,
				" match (u) where u.email='" + username + "'  return u", Collections.emptyMap()));
		LOGGER.debug("Log in " + username);
		if (userNode.isPresent()) {
			String encrypt = aeService.decrypt(userNode.get().getPassword());
			LOGGER.debug("Log in " + encrypt);
			if (encrypt.equals(password)) {
				return Optional.ofNullable(userNode.get());
			}
		}
		return Optional.empty();

	}

	@Override
	public Optional<UserNode> tokenExist(String token) {
		Optional<UserNode> userNode = Optional.empty();
		userNode = Optional.ofNullable(session.queryForObject(UserNode.class,
				"match (u) where  u.token='" + token + "' return u", Collections.emptyMap()));
		return userNode;
	}
}
