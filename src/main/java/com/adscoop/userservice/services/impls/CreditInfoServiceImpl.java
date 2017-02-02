package com.adscoop.userservice.services.impls;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.neo4j.ogm.session.Session;

import com.adscoop.userservice.entites.CreditInfo;
import com.google.inject.Inject;

import rx.Observable;

/**
 * Created by thokle on 03/09/2016.
 */
public class CreditInfoServiceImpl  implements CreditInfoService {

    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;
private Session connectionSource;
    @Inject
    public CreditInfoServiceImpl(Session connectionSource) {


        this.connectionSource = connectionSource;
    }


    @Override
    public Iterable<Map<String, Object>> getCreditInfoByUserId(Long s)  throws IOException{
        try{
        return connectionSource.query("",Collections.EMPTY_MAP);
    } catch (Exception e){


        }
return null;
    }

    @Override
    public Iterable<CreditInfo> findAll() throws  IOException {
        try {
            return connectionSource.loadAll(CreditInfo.class,DEPTH_LIST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CreditInfo findbyId(Long id) throws  IOException{
        try {
            return connectionSource.load(CreditInfo.class,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(CreditInfo entity) throws IOException {
        try {
            if(connectionSource.detachNodeEntity(entity.getId())){
                connectionSource.delete(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException{
        try {
            return Observable.from(connectionSource.query(cypherQuery, Collections.EMPTY_MAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public CreditInfo saveOrUpdate(CreditInfo entity) throws IOException {
        try {
            connectionSource.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findbyId(entity.getId());
    }

    @Override
    public CreditInfo findByUserId(Long id) throws Exception {

        return connectionSource.queryForObject(CreditInfo.class,"match (c)-[:CREDIT_BELONGS_TO_USER]->(u) where u.id="+id,Collections.EMPTY_MAP);
    }

    @Override
    public CreditInfo findByUserToken(String token){
        try {
            return connectionSource.queryForObject(CreditInfo.class,"",Collections.EMPTY_MAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
