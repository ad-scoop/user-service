package com.adscoop.userservice.congfig;

import java.util.UUID;

import com.google.inject.Singleton;

/**
 * Created by thokle on 11/09/2016.
 */

@Singleton
public class TokenService {

    public String generateToken(){
        return UUID.randomUUID().toString();


    }
}
