package com.adscoop.userservice.services.impls;

import com.adscoop.entiites.AccountInformation;

import java.util.Optional;

/**
 * Created by thokle on 27/12/2016.
 */
public interface AccountInformationService {


Optional<AccountInformation> getByUserToken();



}
