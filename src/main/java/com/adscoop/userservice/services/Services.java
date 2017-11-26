package com.adscoop.userservice.services;


import java.util.List;
import java.util.Map;

import ratpack.exec.Promise;
import rx.Observable;

/**
 * Created by thokle on 31/08/2016.
 */
public interface Services<T> {

    Observable<List<T>>findAll();

    T findbyId(Long id);

    void delete(T entity);

    T saveOrUpdate(T entity);

    Promise<Iterable<Map<String,Object>>> findByCypher(String cypherQuery);



}
