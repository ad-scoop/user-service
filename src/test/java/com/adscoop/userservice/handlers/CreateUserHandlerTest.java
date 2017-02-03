package com.adscoop.userservice.handlers;

import com.adscoop.userservice.congfig.TokenService;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.handlers.users.CreateUserHandler;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by kleistit on 02/02/2017.
 */
public class CreateUserHandlerTest {
    @Mock
    UserNodeServiceImpl userNodeService;
    @Mock
    TokenService tokenService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void initialtest() throws Exception {
        when(userNodeService.saveOrUpdate(any(UserNode.class))).thenReturn(userNode());
        when(tokenService.generateToken()).thenReturn("9997cb0a-cb75-476b-9155-437599d9ce08");
        HandlingResult handlingResult = RequestFixture.handle(new CreateUserHandler(userNodeService, tokenService), fix -> {
            fix.method("POST").header("content-type", "application/json").uri("user/create");

        });

       assertTrue(handlingResult.getStatus().getCode() == 200);

    }

    private UserNode userNode() {
        UserNode userNode = new UserNode();
        userNode.setUsername("tho");
        userNode.setPassword("pass");
        userNode.setFirstname("Thomas");
        userNode.setLastname("Kleist");
        return userNode;
    }
}
