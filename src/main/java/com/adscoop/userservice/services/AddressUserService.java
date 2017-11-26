package com.adscoop.userservice.services;


import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import com.adscoop.userservice.entites.AddressNode;

import rx.Observable;

/**
 * Created by thokle on 01/09/2016.
 */
public interface AddressUserService  {

    Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException;

    Iterable<AddressNode> findAll() throws IOException;

    AddressNode findbyId(Long id) throws  IOException;

    void delete(AddressNode entity) throws  IOException;

    Optional<AddressNode> saveOrUpdate(AddressNode entity) throws  IOException;

    Optional<AddressNode> findByUser(String cypher) throws  IOException;

}
