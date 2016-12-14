package com.adscoop.userservice.services.impls;


import org.neo4j.ogm.cypher.query.CypherQuery;
import ratpack.exec.Promise;
import rx.Observable;
import rx.Scheduler;

import java.util.*;

/**
 * Created by thokle on 31/08/2016.
 */
public interface Services<T> {

    Observable<List<T>>findAll();

    T findbyId(Long id);

    void delete(T entity);

    T saveOrUpdate(T entity);

    Iterable<Map<String,Object>> findByCypher(String cypherQuery);




}
