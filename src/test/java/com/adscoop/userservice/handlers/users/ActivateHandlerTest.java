package com.adscoop.userservice.handlers.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.adscoop.userservice.congfig.UserModel;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.handlers.utils.ContextBuilder;
import com.adscoop.userservice.services.impls.IUser;

import ratpack.handling.Context;

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
		UserNode userNode = new UserNode();
		doReturn(Optional.of(userNode))
			.when(userService)
			.findByUserToken(anyString());
		
		// when
		activateHandler.verifyActivation(ContextBuilder.aBuilder().build()).toConsumer().accept("");
		
		// when
		assertThat("user was not activated", userNode.isActivated(), is(Boolean.TRUE));
		verify(userService).saveOrUpdate(eq(userNode));
	}
	
}
