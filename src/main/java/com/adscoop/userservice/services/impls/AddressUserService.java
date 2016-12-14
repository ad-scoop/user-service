package com.adscoop.userservice.services.impls;



import com.adscoop.entiites.AddressNode;
import rx.Observable;

import java.io.IOException;
import java.util.Map;

/**
 * Created by thokle on 01/09/2016.
 */
public interface AddressUserService  {

    Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException;

    Iterable<AddressNode> findAll() throws IOException;

    AddressNode findbyId(Long id) throws  IOException;

    void delete(AddressNode entity) throws  IOException;

    AddressNode saveOrUpdate(AddressNode entity) throws  IOException;

    AddressNode findByUser(String cypher) throws  IOException;

}
