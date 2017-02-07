package com.adscoop.userservice.services.impls;


import java.util.Collections;
import java.util.Optional;

import com.adscoop.userservice.congfig.AEService;
import org.neo4j.ogm.session.Session;

import com.adscoop.userservice.entites.UserNode;
import com.google.inject.Inject;

import javax.annotation.Resource;

/**
 * Created by thokle on 04/12/2016.
 */

public class AuthorazationService implements IAuthorazationService {

    private Session session;
    private AEService aeService;


    @Inject
    public AuthorazationService(Session session, AEService aeService) {
        this.session = session;
        this.aeService = aeService;

    }


    @Override
    public Optional<UserNode> login(String username, String password) {
        String res = null;

        Optional<UserNode> userNode = Optional.ofNullable(session.queryForObject(UserNode.class, " match (u) where u.email='" + username + "'  return u", Collections.EMPTY_MAP));
        if (userNode.isPresent()) {
            String encrypt = aeService.decrypt(userNode.get().getPassword());
            if (encrypt.equals(password)) {
                return Optional.ofNullable(userNode.get());
            }
        } else {
            return Optional.empty();
        }
        return Optional.empty();

    }

    @Override
    public Optional<UserNode> tokenExist(String token) {
        Optional<UserNode> userNode = Optional.empty();

        userNode = Optional.ofNullable(session.queryForObject(UserNode.class, "match (u) where  u.token='" + token + "' return u", Collections.EMPTY_MAP));

        return userNode;
    }
}
