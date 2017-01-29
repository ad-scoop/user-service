package com.adscoop.userservice.modules;


import com.adscoop.entiites.UserNode;
import com.google.inject.Inject;
import org.neo4j.ogm.session.Session;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by thokle on 04/12/2016.
 */
public class AuthorazationService {

    private Session session;



    @Inject
    public AuthorazationService(Session  session){
       this.session = session;

    }



    public Optional<String> login(String username, String password){
        String res = null;

        try {
            UserNode userNode =  session.queryForObject(UserNode.class," match (u) where u.username='"+username+"' and  u.password='"+password +"'   return u",Collections.EMPTY_MAP);

            res =  userNode.getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }   return Optional.of(res);
    }


    public Optional<String> tokenExist(String username, String token){

        String res = null;
        try {

            UserNode userNode = session.queryForObject(UserNode.class,"match (u) where u.username='"+username+ "' and u.token= '"+token +"' return u", Collections.EMPTY_MAP);
            if(userNode != null){
res = userNode.getToken();
            }

            userNode.getToken();
        }catch (Exception e){
            e.toString();
        }
        return Optional.of(res);
    }



}
