package com.adscoop.userservice.modules;


import java.util.Collections;
import java.util.Map;
import java.util.Optional;


import org.neo4j.ogm.session.Session;

import com.adscoop.entiites.UserNode;
import com.google.inject.Inject;

/**
 * Created by thokle on 04/12/2016.
 */
public class AuthorazationService {

    private Session session;



    @Inject
    public AuthorazationService(Session  session){
       this.session = session;

    }



    public Optional<UserNode> login(String username, String password) throws Exception{
        String res = null;

        try {
            UserNode userNode =  session.queryForObject(UserNode.class," match (u) where u.username='"+username+"' and  u.password='"+password +"'   return u",Collections.EMPTY_MAP);
        return  Optional.of(userNode);

        } catch (Exception e) {throw new Exception(e.getMessage());
        }
    }


    public Optional<UserNode> tokenExist(String username, String token)  {
Optional<UserNode> userNode = Optional.empty();

        userNode = Optional.ofNullable(session.queryForObject(UserNode.class, "match (u) where u.username='" + username + "' and u.token= '" + token + "' return u", (Map<String, ?>) Collections.EMPTY_SET));

        return userNode;
    }

}
