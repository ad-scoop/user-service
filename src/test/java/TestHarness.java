import com.adscoop.userservice.handlers.users.GetUserHandler;
import ratpack.test.embed.EmbeddedApp;

/**
 * Created by thokle on 15/09/2016.
 */
public class TestHarness {


    public  static void main(String... args ) throws Exception {


        EmbeddedApp.fromHandlers(chain -> chain.prefix("users", chain1 -> {

            chain1.get("all", ctx -> {
                GetUserHandler getUserHandler = ctx.get(GetUserHandler.class);

            });

        })).test(testHttpClient -> {
            testHttpClient.getResponse().getBody().getInputStream();


        });

    }


}
