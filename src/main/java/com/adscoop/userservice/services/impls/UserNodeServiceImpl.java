package com.adscoop.userservice.services.impls;

import com.adscoop.entiites.UserNode;
import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.adscoop.services.neo4j.connection.ServiceCommonConfig;
import com.google.inject.Inject;


import org.neo4j.ogm.model.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;


import java.io.IOException;

import java.util.Collection;
import java.util.Collections;

import java.util.Map;
import java.util.Optional;

/**
 * Created by thokle on 03/09/2016.
 */

public class UserNodeServiceImpl implements IUser{

    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;
    private static Logger logger = LoggerFactory.getLogger(UserNodeServiceImpl.class);
    private ServiceCommonConfig dataSource;

    @Inject
    public UserNodeServiceImpl(ServiceCommonConfig dataSource) {

        this.dataSource = dataSource;

    }

    @Override
    public Iterable<Map<String, Object>> getUsersByDomainName(String s) {
        return null;
    }

    @Override
    public Optional<UserNode> findByUserNameAndPassword(String username, String password) throws IOException {
        try {
            return Optional.of(dataSource.connectionSource().queryForObject(UserNode.class,"match (a:UserNode) where a.username='"+username+"' and  a.password='"+password+"' return a",Collections.EMPTY_MAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
return null;

    }

    @Override
    public Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException {
        try {
            return Observable.from(dataSource.connectionSource().query(cypherQuery,Collections.EMPTY_MAP)).create( as  -> {


            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    @Override
    public Collection<UserNode> findAll() throws IOException {
        try {
            return dataSource.connectionSource().loadAll(UserNode.class,DEPTH_LIST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Observable<UserNode> getAllUsersNodes() throws IOException {
        try {
            return  Observable.from(dataSource.connectionSource().loadAll(UserNode.class, DEPTH_LIST));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    @Override
    public UserNode findbyId(Long id) throws IOException {
        try {
            return dataSource.connectionSource().load(UserNode.class,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(UserNode entity) throws IOException {
        try {
            dataSource.connectionSource().delete(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserNode saveOrUpdate(UserNode entity) throws IOException {
        //  logger.debug(dataSource.session().getTransaction().status().name());
        try {
            dataSource.connectionSource().save(entity,DEPTH_ENTITY);


        } catch (Exception e) {
            e.printStackTrace();
System.err.print(e);

        }
      return  entity;
    }

    @Override
    public UserNode findByToken(String token) throws IOException {
            try {
                return dataSource.connectionSource().queryForObject(UserNode.class, "MATCH (n:UserNode) where n.token='"+token+"' RETURN n", Collections.EMPTY_MAP);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  null;
        }
}
