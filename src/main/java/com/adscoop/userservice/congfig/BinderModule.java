package com.adscoop.userservice.congfig;

import com.adscoop.userservice.chains.AccountChainHandler;
import com.adscoop.userservice.chains.AddressChainHandler;
import com.adscoop.userservice.chains.CompanyChainHandler;
import com.adscoop.userservice.chains.CreditChainHandler;
import com.adscoop.userservice.chains.UserChainHandler;
import com.adscoop.userservice.exceptions.UserServiceClientExceptionHandler;
import com.adscoop.userservice.handlers.CORSHandler;
import com.adscoop.userservice.handlers.accountInformationHandler.CreateAccountInformationHandler;
import com.adscoop.userservice.handlers.accountInformationHandler.DeleteAccountInformation;
import com.adscoop.userservice.handlers.accountInformationHandler.GetAccountInformationHandler;
import com.adscoop.userservice.handlers.accountInformationHandler.UpdateAccountInformationHandler;
import com.adscoop.userservice.handlers.address.CreateAddressHandler;
import com.adscoop.userservice.handlers.address.DeleteAddressHandler;
import com.adscoop.userservice.handlers.address.GetAdderessHandler;
import com.adscoop.userservice.handlers.address.UpdateAddressHandler;
import com.adscoop.userservice.handlers.auth.AuthHandler;
import com.adscoop.userservice.handlers.auth.LoginHandler;
import com.adscoop.userservice.handlers.company.CreateCompanyHandler;
import com.adscoop.userservice.handlers.company.DeleteCompanyHandler;
import com.adscoop.userservice.handlers.company.GetCompanyHandler;
import com.adscoop.userservice.handlers.company.UpdateCompanyHandler;
import com.adscoop.userservice.handlers.credit.CreateCreditHandler;
import com.adscoop.userservice.handlers.credit.DeleteCreditCardHandler;
import com.adscoop.userservice.handlers.credit.GetCreditHandler;
import com.adscoop.userservice.handlers.credit.UpdateCreditHandler;
import com.adscoop.userservice.handlers.users.ActivateHandler;
import com.adscoop.userservice.handlers.users.CreateUserHandler;
import com.adscoop.userservice.handlers.users.DeleteUserHandler;
import com.adscoop.userservice.handlers.users.GetUserHandler;
import com.adscoop.userservice.handlers.users.UpdateUserHandler;
import com.adscoop.userservice.services.AddressUserService;
import com.adscoop.userservice.services.AddressUserServiceImpl;
import com.adscoop.userservice.services.AuthorazationService;
import com.adscoop.userservice.services.CreditInfoService;
import com.adscoop.userservice.services.CreditInfoServiceImpl;
import com.adscoop.userservice.services.IAuthorazationService;
import com.adscoop.userservice.services.IUser;
import com.adscoop.userservice.services.UserNodeServiceImpl;
import com.google.inject.AbstractModule;

import ratpack.health.HealthCheckHandler;

/**
 * Created by thokle on 29/08/2016.
 */

public class BinderModule extends AbstractModule{
    @Override
    protected void configure() {

        //Services

        bind(IUser.class).to(UserNodeServiceImpl.class).asEagerSingleton();
        bind(AddressUserService.class).to(AddressUserServiceImpl.class).asEagerSingleton();
        bind(CreditInfoService.class).to(CreditInfoServiceImpl.class).asEagerSingleton();
        bind(TokenService.class).asEagerSingleton();
        bind(AEService.class).asEagerSingleton();
        bind(IAuthorazationService.class).to(AuthorazationService.class).asEagerSingleton();
        //UserHandler

        bind(CreateUserHandler.class).asEagerSingleton();
        bind(UpdateUserHandler.class).asEagerSingleton();
        bind(GetUserHandler.class).asEagerSingleton();
        bind(DeleteUserHandler.class).asEagerSingleton();
        //AddressHandler

        bind(CreateAddressHandler.class).asEagerSingleton();
        bind(GetAdderessHandler.class).asEagerSingleton();
        bind(DeleteAddressHandler.class).asEagerSingleton();
        bind(UpdateAddressHandler.class).asEagerSingleton();

        //CompanyHandlser
        bind(DeleteCompanyHandler.class).asEagerSingleton();
        bind(UpdateCompanyHandler.class).asEagerSingleton();
        bind(GetCompanyHandler.class).asEagerSingleton();
        bind(CreateCompanyHandler.class).asEagerSingleton();

        //AccountInformationHandlers
        bind(UpdateAccountInformationHandler.class).asEagerSingleton();
        bind(DeleteAccountInformation.class).asEagerSingleton();
        bind(CreateAccountInformationHandler.class).asEagerSingleton();
        bind(GetAccountInformationHandler.class).asEagerSingleton();


        //CreditHandlers

        bind(CreateCreditHandler.class).asEagerSingleton();
        bind(GetCreditHandler.class).asEagerSingleton();
        bind(UpdateCreditHandler.class).asEagerSingleton();
        bind(DeleteCreditCardHandler.class).asEagerSingleton();


        //Chains

        bind(AddressChainHandler.class).asEagerSingleton();
        bind(CreditChainHandler.class).asEagerSingleton();
        bind(AddressChainHandler.class).asEagerSingleton();
        bind(UserChainHandler.class).asEagerSingleton();
        bind(CompanyChainHandler.class).asEagerSingleton();
        bind(AccountChainHandler.class).asEagerSingleton();

        //AuthHandlers
        bind(LoginHandler.class).asEagerSingleton();
        bind(AuthHandler.class).asEagerSingleton();
        bind(ActivateHandler.class).asEagerSingleton();
        
        //CorsHandler
        bind(CORSHandler.class).asEagerSingleton();


        //ExceptiomHandler
        bind(UserServiceClientExceptionHandler.class).asEagerSingleton();


        //Health
        bind(HealthCheckHandler.class).asEagerSingleton();
	}

}
