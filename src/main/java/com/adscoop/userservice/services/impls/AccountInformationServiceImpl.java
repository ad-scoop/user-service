package com.adscoop.userservice.services.impls;

import java.util.Collections;
import java.util.Optional;

import org.neo4j.ogm.session.Session;

import com.adscoop.entiites.AccountInformation;
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
	public Optional<AccountInformation> getByUserToken() {

		try {
			return Optional.of(session.queryForObject(AccountInformation.class,
					"match (c) where c.accountnr='1231' return c", Collections.EMPTY_MAP));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
