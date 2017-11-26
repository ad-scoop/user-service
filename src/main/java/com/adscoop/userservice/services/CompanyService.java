package com.adscoop.userservice.services;


import java.io.IOException;
import java.util.Map;

import com.adscoop.userservice.entites.Company;



/**
 * Created by thokle on 31/10/2016.
 */
public interface CompanyService {

    void save(Company companyNode) throws IOException;

    Company findByUser(String id, String cypher) throws IOException;

    Company findbyId(long id);

    Map<String,String> getAllForUser(long id);

    void delete(Company company);

}
