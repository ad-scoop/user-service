package com.adscoop.userservice.services.impls;

import java.util.Optional;

import com.adscoop.entiites.AccountInformation;

/**
 * Created by thokle on 27/12/2016.
 */
public interface AccountInformationService {

	Optional<AccountInformation> getByUserToken();

}
