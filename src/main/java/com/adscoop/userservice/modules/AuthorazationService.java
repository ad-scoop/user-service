package com.adscoop.userservice.modules;


import java.util.Collections;
import java.util.Optional;

import org.neo4j.ogm.session.Session;

import com.adscoop.userservice.entites.UserNode;
import com.google.inject.Inject;

/**
 * Created by thokle on 04/12/2016.
 */
public class AuthorazationService {

    private Session session;


    @Inject
    public AuthorazationService(Session session) {
        this.session = session;

    }


    public Optional<UserNode> login(String username, String password) {
        String res = null;

        UserNode userNode = session.queryForObject(UserNode.class, " match (u) where u.email='" + username + "' and  u.password='" + password + "'   return u", Collections.EMPTY_MAP);
        return Optional.ofNullable(userNode);

    }


    public Optional<UserNode> tokenExist(String token) {
        Optional<UserNode> userNode = Optional.empty();

        userNode = Optional.ofNullable(session.queryForObject(UserNode.class, "match (u) where  u.token='"+token+"' return u",Collections.EMPTY_MAP));

        return userNode;
    }

}
