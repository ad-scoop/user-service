package com.adscoop.userservice.modules;


import org.neo4j.ogm.session.Session;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * Created by thokle on 05/01/2017.
 */
public class AuthConfigurableModule extends AbstractModule {





    @Override
    protected void configure() {

    }


    @Provides
    public AuthorazationService authorazationService(Session configure){
        return new AuthorazationService(configure);


    }
}