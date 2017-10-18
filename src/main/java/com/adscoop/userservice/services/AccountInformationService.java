package com.adscoop.userservice.services;

import java.util.Optional;

import com.adscoop.userservice.entites.AccountInformation;



/**
 * Created by thokle on 27/12/2016.
 */
public interface AccountInformationService {

	Optional<AccountInformation> getByUserToken(String token);


	void delete(AccountInformation  accountInformation);

	void update(AccountInformation accountInformation);

}
