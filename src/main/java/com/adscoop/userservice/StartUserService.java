package com.adscoop.userservice;

/**
 * Created by thokle on 18/12/2016.
 */


import com.adscoop.com.adscoop.services.AuthConfigurableModule;

import com.adscoop.services.neo4j.connection.*;
import com.adscoop.userservice.congfig.BinderModule;


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


import ratpack.guice.Guice;

import ratpack.rx.RxRatpack;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

/**
 * Created by thokle on 29/08/2016.
 */
public class StartUserService {


    public static void main(String... args) throws Exception {
        RxRatpack.initialize();

        RatpackServer.start(ratpackServerSpec -> ratpackServerSpec
                .serverConfig(serverConfigBuilder -> serverConfigBuilder.baseDir(BaseDir.find())
                        .yaml("ratpack.yaml").require("/db", Config.class)
                        .env().sysProps().port(8181)).registry(Guice.registry(bindingsSpec -> bindingsSpec.module(BinderModule.class).module(ServiceCommonConfigModule.class).module(AuthConfigurableModule.class)))
                .handlers(chain -> chain.prefix("user", user -> user.all(AuthHandler.class).delete(DeleteUserHandler.class).put(UpdateUserHandler.class))
                        .prefix("address", adr -> adr.all(AuthHandler.class).post(CreateAddressHandler.class).delete(DeleteAddressHandler.class).put(UpdateAddressHandler.class).get(GetAdderessHandler.class))
                        .prefix("company", com -> com.all(AuthHandler.class).post(CreateCompanyHandler.class).delete(DeleteCompanyHandler.class).put(UpdateCompanyHandler.class).get(GetCompanyHandler.class)).
                                prefix("account", acc -> acc.all(AuthHandler.class).post(CreateAccountInformationHandler.class).delete(DeleteAccountInformation.class).put(UpdateAccountInformationHandler.class).get(GetAccountInformationHandler.class))
                        .prefix("credit", cre -> cre.all(AuthHandler.class).post(CreateCreditHandler.class).delete(DeleteCreditCardHandler.class).put(UpdateCreditHandler.class).get(GetCreditHandler.class))
                        .prefix("", chain1 -> chain1.post("create",CreateUserHandler.class).post("login",LoginHandler.class))));





}
}
