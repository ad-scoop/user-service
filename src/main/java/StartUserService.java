import com.adscoop.AuthServerStarter;
import com.adscoop.com.adscoop.services.AuthorazationService;
import com.adscoop.conf.ConfigurationAuth;
import com.adscoop.services.neo4j.connection.ServiceCommonConfig;
import com.adscoop.userservice.congfig.BinderModule;
import com.adscoop.userservice.congfig.DataBaseConfig;

import com.adscoop.userservice.handlers.accountInformationHandler.CreateAccountInformationHandler;
import com.adscoop.userservice.handlers.accountInformationHandler.GetAccountInformationHandler;
import com.adscoop.userservice.handlers.address.CreateAddressHandler;
import com.adscoop.userservice.handlers.address.GetAdderessHandler;
import com.adscoop.userservice.handlers.address.UpdateAddressHandler;
import com.adscoop.userservice.handlers.auth.AuthHandler;
import com.adscoop.userservice.handlers.company.CreateCompanyHandler;
import com.adscoop.userservice.handlers.credit.CreateCreditHandler;
import com.adscoop.userservice.handlers.credit.GetCreditHandler;
import com.adscoop.userservice.handlers.users.CreateUserHandler;
import com.adscoop.userservice.handlers.users.GetUserHandler;
import com.adscoop.userservice.handlers.users.UpdateUserHandler;
import org.neo4j.cypher.internal.compiler.v1_9.mutation.Update;
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
                bindingsSpec.module(BinderModule.class).module(ServiceCommonConfig.class).module(ConfigurationAuth.class))).serverConfig(serverConfigBuilder -> serverConfigBuilder.env("userService").port(8181).yaml(StartUserService.class.getResource("ratpack.yaml")).require("/dataBase",DataBaseConfig.class)).handlers(
                handlers ->handlers.prefix("admin",chain -> {
                    chain.all(AuthHandler.class).prefix("user",user -> user.put("update", UpdateUserHandler.class));



                })));

                        };

}
