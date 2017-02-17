package com.adscoop.userservice;

import com.adscoop.userservice.chains.AccountChainHandler;
import com.adscoop.userservice.chains.AddressChainHandler;
import com.adscoop.userservice.chains.CompanyChainHandler;
import com.adscoop.userservice.chains.CreditChainHandler;
import com.adscoop.userservice.chains.UserChainHandler;
import com.adscoop.userservice.congfig.BinderModule;
import com.adscoop.userservice.exceptions.UserServiceClientExceptionHandler;
import com.adscoop.userservice.handlers.CORSHandler;
import com.adscoop.userservice.handlers.auth.LoginHandler;
import com.adscoop.userservice.handlers.users.ActivateHandler;
import com.adscoop.userservice.handlers.users.CreateUserHandler;
import com.adscoop.userservice.modules.Config;
import com.adscoop.userservice.modules.ServiceCommonConfigModule;

import ratpack.dropwizard.metrics.DropwizardMetricsConfig;
import ratpack.dropwizard.metrics.DropwizardMetricsModule;
import ratpack.guice.Guice;
import ratpack.handling.Context;
import ratpack.health.HealthCheckHandler;
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
                        .yaml("ratpack.yaml")
                        .require("/db", Config.class)
                        .props("ratpack.properties").sysProps()
                        .env()
                        .development(true)
                        .build())
                .registry(Guice.registry(bindingsSpec -> bindingsSpec.module(BinderModule.class)
                		.module(ServiceCommonConfigModule.class)
                        .module(DropwizardMetricsModule.class, d -> {
                            d.console();
                            d.getSlf4j();
                            d.getGraphite();
                            d.getJmx();


                }))).handlers(chain ->  chain.prefix("user" ,
                            userchain -> userchain.all(CORSHandler.class)
						.post("create", CreateUserHandler.class)
						.post("login",	LoginHandler.class)
						.post("activate", ActivateHandler.class))));


    }	
    
}
