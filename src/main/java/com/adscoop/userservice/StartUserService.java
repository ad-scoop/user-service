package com.adscoop.userservice;

/**
 * Created by thokle on 18/12/2016.
 */

import com.adscoop.conf.ConfigurationAuth;
import com.adscoop.services.neo4j.connection.ServiceCommonConfig;
import com.adscoop.userservice.congfig.BinderModule;
import com.adscoop.userservice.congfig.DataBaseConfig;

import com.adscoop.userservice.handlers.CreditCardHandler;
import com.adscoop.userservice.handlers.accountInformationHandler.CreateAccountInformationHandler;
import com.adscoop.userservice.handlers.accountInformationHandler.DeleteAccountInformation;
import com.adscoop.userservice.handlers.accountInformationHandler.GetAccountInformationHandler;
import com.adscoop.userservice.handlers.accountInformationHandler.UpdateAccountInformationHandler;
import com.adscoop.userservice.handlers.address.CreateAddressHandler;
import com.adscoop.userservice.handlers.address.DeleteAddressHandler;
import com.adscoop.userservice.handlers.address.UpdateAddressHandler;
import com.adscoop.userservice.handlers.auth.AuthHandler;

import com.adscoop.userservice.handlers.auth.LoginHandler;
import com.adscoop.userservice.handlers.company.CreateCompanyHandler;
import com.adscoop.userservice.handlers.company.DeleteCompanyHandler;
import com.adscoop.userservice.handlers.company.GetCompanyHandler;
import com.adscoop.userservice.handlers.company.UpdateCompanyHandler;
import com.adscoop.userservice.handlers.credit.DeleteCreditCardHandler;
import com.adscoop.userservice.handlers.credit.GetCreditHandler;
import com.adscoop.userservice.handlers.credit.UpdateCreditHandler;
import com.adscoop.userservice.handlers.users.CreateUserHandler;
import com.adscoop.userservice.handlers.users.DeleteUserHandler;
import com.adscoop.userservice.handlers.users.GetUserHandler;
import com.adscoop.userservice.handlers.users.UpdateUserHandler;


import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.RatpackServer;

/**
 * Created by thokle on 29/08/2016.
 */
public class StartUserService {


    public static void main(String... args) throws Exception {
        RxRatpack.initialize();
        RatpackServer.start(rSSpec -> rSSpec.registry(Guice.registry(bindingsSpec ->
                bindingsSpec.module(BinderModule.class).module(ServiceCommonConfig.class).module(ServiceCommonConfig.class).module(ConfigurationAuth.class))).serverConfig(serverConfigBuilder -> serverConfigBuilder.env("userService").port(8181))
                        .handlers( chain -> {

                   chain.prefix("admin" , admin -> admin.all(AuthHandler.class).prefix("user", admUser -> {  admUser.put(UpdateUserHandler.class).delete(DeleteUserHandler.class).get(GetUserHandler.class).prefix("address", adminAdr -> {
                       adminAdr.put(UpdateAddressHandler.class).delete(DeleteAddressHandler.class).post(CreateAddressHandler.class).post(CreateAddressHandler.class)
                               .prefix("creditcard", adch -> {
                           adch.post(CreditCardHandler.class).get(GetCreditHandler.class).put(UpdateCreditHandler.class).delete(DeleteCreditCardHandler.class);

                       }).prefix("account" ,acount  -> acount.post(CreateAccountInformationHandler.class).get(GetAccountInformationHandler.class).put(UpdateAccountInformationHandler.class).delete(DeleteAccountInformation.class))
                               .prefix("company", comch -> { comch.post(CreateCompanyHandler.class).get(GetCompanyHandler.class).put(UpdateCompanyHandler.class).delete(DeleteCompanyHandler.class);

                               });

                   });


                   }));
                }).handlers( chain -> { chain.prefix("user", chain1 ->  {
                        chain1.post("create",CreateUserHandler.class).post("login", LoginHandler.class);


                });


}));

}
}
