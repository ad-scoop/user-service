package com.adscoop.userservice.services.impls;

import com.adscoop.entiites.Company;
import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.google.inject.Inject;


import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by thokle on 31/10/2016.
 */
public class CompanyServiceImpl implements  CompanyService {

ConnectionSource connectionSource;
    @Inject
    public CompanyServiceImpl(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    @Override
    public void save(Company companyNode) throws IOException {
        try {
            connectionSource.session().save(companyNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Company findByUser(String id, String companyname) throws IOException {


        try {
            return connectionSource.session().queryForObject(Company.class,"match (u:UserNode)-[:USER_HAS_COMPANY]->(c:Company ) where c.companyname='"+ companyname  +"' and u.token='" + id  + "'  return c", Collections.EMPTY_MAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Company findbyId(long id) {
        return null;
    }

    @Override
    public Map<String, String> getAllForUser(long id) {
        return null;
    }
}
