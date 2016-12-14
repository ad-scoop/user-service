package com.adscoop.userservice.services.impls;

import com.adscoop.entiites.CreditInfo;
import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.google.inject.Inject;

import rx.Observable;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thokle on 03/09/2016.
 */
public class CreditInfoServiceImpl  implements CreditInfoService {

private ConnectionSource connectionSource;
    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;
    @Inject
    public CreditInfoServiceImpl(ConnectionSource connectionSource) {


        this.connectionSource = connectionSource;
    }


    @Override
    public Iterable<Map<String, Object>> getCreditInfoByUserId(Long s)  throws IOException{
        return connectionSource.session().query("",Collections.EMPTY_MAP);
    }

    @Override
    public Iterable<CreditInfo> findAll() throws  IOException {
        return connectionSource.session().loadAll(CreditInfo.class,DEPTH_LIST);
    }

    @Override
    public CreditInfo findbyId(Long id) throws  IOException{
        return connectionSource.session().load(CreditInfo.class,id);
    }

    @Override
    public void delete(CreditInfo entity) throws IOException {
            connectionSource.session().delete(entity);
    }



    @Override
    public Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException{
        return Observable.from(connectionSource.session().query(cypherQuery, Collections.EMPTY_MAP));
    }


    @Override
    public CreditInfo saveOrUpdate(CreditInfo entity) throws IOException {
        connectionSource.session().save(entity);
        return findbyId(entity.getId());
    }

    @Override
    public CreditInfo findByUserId(Long id) throws Exception {
        Map<String, ? > parameters  = new HashMap<>();
        return connectionSource.session().queryForObject(CreditInfo.class,"match (c)-[:CREDIT_BELONGS_TO_USER]->(u) where u.id="+id,parameters);
    }
}
