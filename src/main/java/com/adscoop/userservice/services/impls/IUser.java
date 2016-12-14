package com.adscoop.userservice.services.impls;


import com.adscoop.entiites.UserNode;
import org.neo4j.ogm.model.Result;
import rx.Observable;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by thokle on 01/09/2016.
 */
public interface IUser {

   Iterable<Map<String ,Object>> getUsersByDomainName(String s) throws IOException;


   Result findByUserNameAndPassword(String username, String password) throws  IOException;

   Observable<UserNode> getAllUsersNodes() throws  IOException;

    Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException;

   Collection<UserNode> findAll() throws  IOException;

   UserNode findbyId(Long id) throws  IOException;

   void delete(UserNode entity) throws  IOException;

   UserNode saveOrUpdate(UserNode entity) throws  IOException;


   UserNode findByToken(String token) throws  IOException;
}
