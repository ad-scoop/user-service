package com.adscoop.userservice.congfig;


import com.adscoop.userservice.chains.*;
import com.adscoop.userservice.handlers.CORSHandler;
import com.adscoop.userservice.handlers.CreditCardHandler;
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
import com.adscoop.userservice.handlers.users.CreateUserHandler;
import com.adscoop.userservice.handlers.users.DeleteUserHandler;
import com.adscoop.userservice.handlers.users.GetUserHandler;
import com.adscoop.userservice.handlers.users.UpdateUserHandler;
import com.adscoop.userservice.services.impls.*;
import com.google.inject.AbstractModule;


/**
 * Created by thokle on 29/08/2016.
 */
public class BinderModule extends AbstractModule{
    @Override
    protected void configure() {


        bind(CreditCardHandler.class).asEagerSingleton();
        bind(IUser.class).to(UserNodeServiceImpl.class).asEagerSingleton();
        bind(AddressUserService.class).to(AddressUserServiceImpl.class).asEagerSingleton();
        bind(CreditInfoService.class).to(CreditInfoServiceImpl.class).asEagerSingleton();
        bind(CreateUserHandler.class).asEagerSingleton();
        bind(UpdateUserHandler.class).asEagerSingleton();
        bind(GetUserHandler.class).asEagerSingleton();
        bind(CreateAddressHandler.class).asEagerSingleton();
        bind(GetAdderessHandler.class).asEagerSingleton();
        bind(UpdateAddressHandler.class).asEagerSingleton();
        bind(CreateCreditHandler.class).asEagerSingleton();
        bind(GetCreditHandler.class).asEagerSingleton();
        bind(UpdateCreditHandler.class).asEagerSingleton();
        bind(TokenService.class).asEagerSingleton();
        bind(CreateCompanyHandler.class).asEagerSingleton();

        bind(CreateAccountInformationHandler.class).asEagerSingleton();
        bind(CreditCardValidatorUtil.class).asEagerSingleton();
        bind(GetAccountInformationHandler.class).asEagerSingleton();
        bind(AuthHandler.class).asEagerSingleton();
        bind(LoginHandler.class).asEagerSingleton();
        bind(DeleteAddressHandler.class).asEagerSingleton();
        bind(DeleteCreditCardHandler.class).asEagerSingleton();

bind(DeleteUserHandler.class).asEagerSingleton();
bind(CreateUserHandler.class).asEagerSingleton();
bind(DeleteCompanyHandler.class).asEagerSingleton();
bind(UpdateCompanyHandler.class).asEagerSingleton();
bind(GetCompanyHandler.class).asEagerSingleton();
bind(DeleteAccountInformation.class).asEagerSingleton();
bind(UpdateAccountInformationHandler.class).asEagerSingleton();
bind(CreditChainHandler.class).asEagerSingleton();
bind(AddressChainHandler.class).asEagerSingleton();
bind(UserChainHandler.class).asEagerSingleton();
bind(CompanyChainHandler.class).asEagerSingleton();
bind(AccountChainHandler.class).asEagerSingleton();
bind(CORSHandler.class).asEagerSingleton();
    }




}
