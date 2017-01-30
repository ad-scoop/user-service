package com.adscoop.userservice.services.impls;


import java.util.List;
import java.util.Map;

import rx.Observable;

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
