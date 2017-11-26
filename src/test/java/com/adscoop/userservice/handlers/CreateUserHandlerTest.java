package com.adscoop.userservice.handlers;

import com.adscoop.userservice.congfig.AEService;
import com.adscoop.userservice.congfig.TokenService;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.handlers.users.CreateUserHandler;
import com.adscoop.userservice.services.UserNodeServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ratpack.server.BaseDir;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import java.io.FileOutputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;

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


    @Mock
    AEService aeService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void initialtest() throws Exception {
        when(userNodeService.saveOrUpdate(any(UserNode.class))).thenReturn(userNode());
        when(tokenService.generateToken()).thenReturn("9997cb0a-cb75-476b-9155-437599d9ce08");
        HandlingResult handlingResult = RequestFixture.handle(new CreateUserHandler(userNodeService, tokenService, aeService), fix -> {
            fix.method("POST").header("content-type", "application/json").uri("user/create");

        });

    }

    private UserNode userNode() {
       return UserNode.builder().firstname("test").lastname("test").build();

           }
}
