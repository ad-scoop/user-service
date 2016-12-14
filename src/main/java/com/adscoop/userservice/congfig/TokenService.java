package com.adscoop.userservice.congfig;

import com.google.inject.Singleton;

import java.util.UUID;

/**
 * Created by thokle on 11/09/2016.
 */

@Singleton
public class TokenService {

    public String generateToken(){
        return UUID.randomUUID().toString();


    }
}
