package test.com.adscoop.userservice.services.impls;

import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.ogm.session.Session;
import ratpack.exec.ExecResult;
import ratpack.exec.Promise;
import ratpack.test.exec.ExecHarness;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by thokle on 22/04/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserNodeServiceImplTest {

    @Mock
    Session session;

    UserNodeServiceImpl userNodeService;

    @Before
    public  void setup(){
        userNodeService = new UserNodeServiceImpl(session);
    }

    @Test
    public void verifyfiFndByUserTokenReturnsUser() throws Exception {

        try (ExecHarness execHarness = ExecHarness.harness()) {

          UserNode userNode =   UserNode.builder().firstname("fname").lastname("lname").email("email@email.dk").build();
            when(session.queryForObject(eq(UserNode.class), anyString(), anyMapOf(String.class, String.class))).thenReturn(userNode);


            userNodeService = new UserNodeServiceImpl(session);
            ExecResult<UserNode> result = execHarness.yield(execution -> userNodeService.findByUserToken(""));


            assertEquals("fname", result.getValue().getFirstname());
            assertEquals("lname", result.getValue().getLastname());
            assertEquals("email@email.dk",result.getValue().getEmail());
            verify(session, times(1)).queryForObject(eq(UserNode.class), anyString(), anyMapOf(String.class, String.class));

        }

    }


}
