package com.adscoop.userservice.services.impls;

import com.adscoop.entiites.UserNode;
import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.google.inject.Inject;


import org.neo4j.ogm.model.Result;

import rx.Observable;


import java.io.IOException;

import java.util.Collection;
import java.util.Collections;

import java.util.Map;

/**
 * Created by thokle on 03/09/2016.
 */
public class UserNodeServiceImpl implements IUser{

    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;

    private ConnectionSource dataSource;

    @Inject
    public UserNodeServiceImpl(ConnectionSource dataSource) {

        this.dataSource = dataSource;

    }

    @Override
    public Iterable<Map<String, Object>> getUsersByDomainName(String s) {
        return null;
    }

    @Override
    public Result findByUserNameAndPassword(String username, String password) throws IOException {
      return dataSource.session().query("match (a:UserNode {username:"+username+", password:"+password+"})",Collections.EMPTY_MAP);


    }

    @Override
    public Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException {
     return Observable.from(dataSource.session().query(cypherQuery,Collections.EMPTY_MAP)).create( as  -> {


     });



    }


    @Override
    public Collection<UserNode> findAll() throws IOException {
        return dataSource.session().loadAll(UserNode.class,DEPTH_LIST);
    }

    @Override
    public Observable<UserNode> getAllUsersNodes() throws IOException {
            return  Observable.from(dataSource.session().loadAll(UserNode.class, DEPTH_LIST));
    }




    @Override
    public UserNode findbyId(Long id) throws IOException {
        return dataSource.session().load(UserNode.class,id);
    }

    @Override
    public void delete(UserNode entity) throws IOException {
dataSource.session().delete(entity);
    }

    @Override
    public UserNode saveOrUpdate(UserNode entity) throws IOException {
         dataSource.session().save(entity);
        return findbyId(entity.getId());
    }


    @Override
    public UserNode findByToken(String token) throws IOException {
        return dataSource.session().queryForObject(UserNode.class, "MATCH (n:UserNode) where n.token='"+token+"' RETURN n", Collections.EMPTY_MAP);
    }
}
