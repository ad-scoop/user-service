package com.adscoop.userservice.handlers.auth;

import com.adscoop.com.adscoop.services.AuthConfigurableModule;
import com.adscoop.com.adscoop.services.AuthorazationService;
import com.adscoop.entiites.UserNode;
import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.adscoop.userservice.congfig.UserModel;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;
import org.neo4j.ogm.session.Session;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Optional;

import static ratpack.jackson.Jackson.fromJson;

/**
 * Created by thokle on 12/12/2016.
 */
public class LoginHandler  implements Handler{

    Session connectionSource;
    AuthConfigurableModule authorazationService;
@Inject
    public LoginHandler(Session connectionSource, AuthConfigurableModule authorazationService) {
        this.connectionSource = connectionSource;
        this.authorazationService = authorazationService;
    }

    @Override
    public void handle(Context ctx) throws Exception {

     AuthorazationService authorazationService =    this.authorazationService.authorazationService(connectionSource);
ctx.parse(fromJson(UserModel.class)).then( userModel ->  {

    Optional<String>  st = authorazationService.login(userModel.getUsername(),userModel.getPassword());

    if(st.isPresent()){
        ctx.getResponse().getHeaders().add("user-token",st.get());
        ctx.render(st.get());

    }else
    {
        ctx.render("not working");

    }




});


    }
}
