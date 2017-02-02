package com.adscoop.userservice.services.impls;

import java.util.Collections;
import java.util.Optional;

import org.neo4j.ogm.session.Session;

import com.adscoop.userservice.entites.AccountInformation;
import com.google.inject.Inject;

/**
 * Created by thokle on 27/12/2016.
 */
public class AccountInformationServiceImpl implements AccountInformationService {

	private Session session;

	@Inject
	public AccountInformationServiceImpl(Session connectionSource) {
		this.session = connectionSource;
	}

	@Override
	public Optional<AccountInformation> getByUserToken(String token) {

		try {
			return Optional.ofNullable(session.queryForObject(AccountInformation.class,
					"match (a)<-[:HAS_ACCOUNT_INFORMATION]-(u) where u.token='"+token+"' return  a", Collections.EMPTY_MAP));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}


	@Override
	public void delete(AccountInformation accountInformation) {
		if(session.detachNodeEntity(accountInformation.getId())){
		session.delete(accountInformation);
	}
	}

	@Override
	public void update(AccountInformation accountInformation) {
		session.save(accountInformation);
	}
}
