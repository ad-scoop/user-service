package com.adscoop.userservice.services.impls;

import com.adscoop.entiites.AccountInformation;
import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.google.inject.Inject;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by thokle on 27/12/2016.
 */
public class AccountInformationServiceImpl implements AccountInformationService {

    private ConnectionSource connectionSource;


    @Inject
    public AccountInformationServiceImpl(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    @Override
    public Optional<AccountInformation> getByUserToken() {

        try {
            return Optional.of(connectionSource.session().queryForObject(AccountInformation.class,"match (c) where c.accountnr='1231' return c", Collections.EMPTY_MAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
return Optional.empty();
    }
}
