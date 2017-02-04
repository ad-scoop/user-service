package com.adscoop.userservice;

/**
 * Created by thokle on 18/12/2016.
 */

import com.adscoop.userservice.chains.*;
import com.adscoop.userservice.congfig.BinderModule;
import com.adscoop.userservice.handlers.CORSHandler;
import com.adscoop.userservice.handlers.auth.LoginHandler;
import com.adscoop.userservice.handlers.users.CreateUserHandler;
import com.adscoop.userservice.modules.AuthConfigurableModule;
import com.adscoop.userservice.modules.Config;
import com.adscoop.userservice.modules.ServiceCommonConfigModule;

import ratpack.dropwizard.metrics.DropwizardMetricsModule;
import ratpack.func.Action;
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
                        .yaml("ratpack.yaml")
                        .require("/db", Config.class)
                        .props("ratpack.properties").sysProps()
                        .env().onError(Action.throwException())
                        .development(true)
                        .build())
                .registry(Guice.registry(bindingsSpec -> bindingsSpec.module(BinderModule.class)
                		.module(ServiceCommonConfigModule.class)
                        .module(DropwizardMetricsModule.class, d -> {
                            d.console();
                            d.getSlf4j();
                            d.getGraphite();
                            d.getJmx();

                })))
                .handlers(chain -> 
                	chain
                		.all(CORSHandler.class)
                		.prefix("useradmin", UserChainHandler.class)
                		.prefix("address", AddressChainHandler.class)
                		.prefix("company", CompanyChainHandler.class)
                		.prefix("account", AccountChainHandler.class)
                        .prefix("credit", CreditChainHandler.class)
                        .prefix("user",chain1 ->
                        	chain1.all(CORSHandler.class)
                        		.post("create", CreateUserHandler.class)
                        		.post("login",LoginHandler.class))
                        		.get("", ctx -> ctx.render("welcome to index"))));
    }	
    
}
