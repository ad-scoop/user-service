package com.adscoop.userservice.handlers.users;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.sun.nio.sctp.HandlerResult;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.handlers.utils.ContextBuilder;
import com.adscoop.userservice.services.impls.IUser;

import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.http.TypedData;
import ratpack.test.exec.ExecHarness;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

@RunWith(MockitoJUnitRunner.class)
public class ActivateHandlerTest {

	@Mock
	private IUser userService;
	
	private ActivateHandler activateHandler;
	
	@Before
	public void setUp() {
		activateHandler = new ActivateHandler(userService);
	}

	@Ignore
	@Test
	public void verifyThatAUserIsActivated() throws Exception {
		try (ExecHarness execHarness = ExecHarness.harness()) {
			// given
			UserNode userNode = UserNode.builder().email("activate@activated-dl").activated(true).build();

		when(userService.findByUserToken(anyString())).thenReturn(  Promise.value(userNode));


			HandlingResult handlingResult =  RequestFixture.handle(new ActivateHandler(userService), requestFixture -> {
				requestFixture.method("POST").header("content-type", "application/json").uri("user/activate");

			});
			// when

			//
			assertEquals(200,handlingResult.getStatus());

			//verify(userService,times(1)).findByUserToken(anyString());


							}
	}
	@Ignore
	@Test
	public void verifyThatIfNoUserActivationExistAnErrorIsReturnd() throws Exception {
		// given
		doReturn(Promise.value(any(UserNode.class)))
			.when(userService)
			.findByUserToken(anyString());
		
		Context ctx = ContextBuilder.aBuilder().build();

		// when
		activateHandler.verifyActivation(ctx).toConsumer().accept(mock(TypedData.class));
		
		// when
		verify(ctx).clientError(eq(410));
	}
	
	
}
