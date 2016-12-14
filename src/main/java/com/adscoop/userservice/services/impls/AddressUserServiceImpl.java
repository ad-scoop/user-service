package com.adscoop.userservice.services.impls;

import com.adscoop.entiites.AddressNode;
import com.adscoop.services.neo4j.connection.ConnectionSource;

import com.google.inject.Inject;


import rx.Observable;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thokle on 01/09/2016.
 */
public class AddressUserServiceImpl implements AddressUserService {
private ConnectionSource connectionSource;
    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;

    @Inject
    public AddressUserServiceImpl(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }



    @Override
    public Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException {


        return Observable.from(connectionSource.session().query(cypherQuery,Collections.EMPTY_MAP));
    }

    @Override
    public Iterable<AddressNode> findAll() throws  IOException{
        return connectionSource.session().loadAll(AddressNode.class,DEPTH_ENTITY);

    }

    @Override
    public AddressNode findbyId(Long id) throws IOException {
        return connectionSource.session().load(AddressNode.class,id);
    }

    @Override
    public void delete(AddressNode entity) throws  IOException{
    connectionSource.session().delete(entity);
    }

    @Override
    public AddressNode saveOrUpdate(AddressNode entity) throws IOException{
         connectionSource.session().save(entity);
        return findbyId(entity.getId());
    }


    @Override
    public AddressNode  findByUser(String cypher) throws IOException {
        Map<String, String> paramaters = new HashMap();
        paramaters.put("id",cypher);
        return connectionSource.session().queryForObject(AddressNode.class,"match (a)-[:ADDRESS_BELONGS_TO_COMPANY]->(c)-[:COMPANY_BELONGS_TO_USER]->(u) where u.id='"+cypher+ "' return a as AddressNode",paramaters);
    }
}
