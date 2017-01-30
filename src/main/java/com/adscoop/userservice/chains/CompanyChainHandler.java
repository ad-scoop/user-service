package com.adscoop.userservice.chains;

import com.adscoop.userservice.handlers.auth.AuthHandler;
import com.adscoop.userservice.handlers.company.CreateCompanyHandler;
import com.adscoop.userservice.handlers.company.DeleteCompanyHandler;
import com.adscoop.userservice.handlers.company.GetCompanyHandler;
import com.adscoop.userservice.handlers.company.UpdateCompanyHandler;

import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by thokle on 15/01/2017.
 */
public class CompanyChainHandler implements Action<Chain> {


    @Override
    public void execute(Chain chain) throws Exception {
      chain.all(AuthHandler.class).post(CreateCompanyHandler.class).delete(DeleteCompanyHandler.class).put(UpdateCompanyHandler.class).get(GetCompanyHandler.class);
    }
}
