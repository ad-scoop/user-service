package com.adscoop.userservice.handlers.users;

import com.adscoop.userservice.entites.UserNode;

import com.adscoop.userservice.services.impls.IUser;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ratpack.exec.Promise;
import ratpack.http.Status;
import ratpack.http.internal.DefaultStatus;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActivateHandlerTest {

    @Mock
    private IUser userService;

    private ActivateHandler activateHandler;

    @Before
    public void setUp() {
        activateHandler = new ActivateHandler(userService);
    }


    @Test
    public void verifyThatAUserIsActivated() throws Exception {

        // given
        UserNode userNode = UserNode.builder().email("activate@activated-dl").activated(true).build();

        when(userService.findByUserToken(anyString())).thenReturn(Promise.value(userNode));


        HandlingResult handlingResult = RequestFixture.handle(new ActivateHandler(userService), requestFixture -> {
            requestFixture.method("POST").header("content-type", "application/json").uri("user/activate");

        });
        // when

        //
        assertEquals(Status.OK, handlingResult.getStatus());

        verify(userService, times(1)).findByUserToken(anyString());


    }



    @Test
    public void verifyThatIfNoUserActivationExistAnErrorIsReturnd() throws Exception {
        // given
       UserNode userNode = UserNode.builder().build();

       when(userService.findByUserToken(anyString())).thenReturn(Promise.value(userNode));


       HandlingResult handlingResult =  RequestFixture.handle(new ActivateHandler(userService), requestFixture -> {
           requestFixture.method("POST").header("content-type", "application/json").header("token","232323effmdvæmv21e1").uri("user/activate");

        });



        assertEquals(DefaultStatus,handlingResult.getStatus());

    }


}
