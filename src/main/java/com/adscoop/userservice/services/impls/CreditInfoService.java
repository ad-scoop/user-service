package com.adscoop.userservice.services.impls;

import java.io.IOException;
import java.util.Map;

import com.adscoop.entiites.CreditInfo;

import rx.Observable;

/**
 * Created by thokle on 03/09/2016.
 */
public interface CreditInfoService {

    Iterable<Map<String ,Object>> getCreditInfoByUserId(Long s) throws IOException;






    Observable<Map<String, Object>> findByCypher(String cypherQuery) throws  IOException;

    Iterable<CreditInfo> findAll() throws IOException;

    CreditInfo findbyId(Long id) throws  IOException;

    void delete(CreditInfo entity) throws IOException;

    CreditInfo saveOrUpdate(CreditInfo entity) throws  IOException;

    CreditInfo findByUserId(Long id) throws  Exception;


    CreditInfo findByUserToken(String token);

}
