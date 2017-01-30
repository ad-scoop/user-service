package com.adscoop.userservice.modules;


import org.neo4j.ogm.session.Session;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

/**
 * Created by thokle on 03/12/2016.
 */
public class ConfigurationAuth extends AbstractModule {


    @Override
    protected void configure() {

    }






    @Provides
    @Inject
    public  AuthorazationService authorazationService(Session neo4JDataSource){
        return new AuthorazationService(neo4JDataSource);
    }




}
