package com.adscoop.userservice.modules;


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
@Resource()
public class AuthorazationService {

    private Session session;
    private AEService aeService;


    @Inject
    public AuthorazationService(Session session, AEService aeService) {
        this.session = session;
        this.aeService = aeService;

    }


    public Optional<UserNode> login(String username, String password) {
        String res = null;
        String decryptedPassword = aeService.decrypt(password, "thisIsAdScoopSecret");
        UserNode userNode = session.queryForObject(UserNode.class, " match (u) where u.email='" + username + "' and  u.password='" + decryptedPassword + "'   return u", Collections.EMPTY_MAP);
        return Optional.ofNullable(userNode);

    }


    public Optional<UserNode> tokenExist(String token) {
        Optional<UserNode> userNode = Optional.empty();

        userNode = Optional.ofNullable(session.queryForObject(UserNode.class, "match (u) where  u.token='"+token+"' return u",Collections.EMPTY_MAP));

        return userNode;
    }

}
