package com.adscoop.userservice.services;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.neo4j.ogm.session.Session;

import com.adscoop.userservice.entites.Company;
import com.google.inject.Inject;

/**
 * Created by thokle on 31/10/2016.
 */
public class CompanyServiceImpl implements  CompanyService {

Session connectionSource;
    @Inject
    public CompanyServiceImpl(Session connectionSource) {
        this.connectionSource = connectionSource;
    }

    @Override
    public void save(Company companyNode) throws IOException {
        try {
            connectionSource.save(companyNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Company findByUser(String id, String companyname) throws IOException {


        try {
            return connectionSource.queryForObject(Company.class,"match (u:UserNode)-[:USER_HAS_COMPANY]->(c:Company ) where c.companyname='"+ companyname  +"' and u.token='" + id  + "'  return c", Collections.emptyMap());
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

    @Override
    public void delete(Company company) {
        if(connectionSource.detachNodeEntity(company.getId())){
            connectionSource.delete(company);

        }
    }
}
