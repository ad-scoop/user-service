package com.adscoop.userservice.services.impls;

import com.adscoop.userservice.entites.UserNode;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by thokle on 04/02/2017.
 */
public interface IAuthorazationService {
     Optional<UserNode> login(String username, String password);

     Optional<UserNode> tokenExist(String token);

}
