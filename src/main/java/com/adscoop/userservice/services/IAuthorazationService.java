package com.adscoop.userservice.services;

import java.util.Optional;

import com.adscoop.userservice.entites.UserNode;

/**
 * Created by thokle on 04/02/2017.
 */
public interface IAuthorazationService {
     Optional<UserNode> login(String username, String password);

     Optional<UserNode> tokenExist(String token);

}
