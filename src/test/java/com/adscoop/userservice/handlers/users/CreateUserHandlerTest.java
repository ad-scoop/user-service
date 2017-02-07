package com.adscoop.userservice.handlers.users;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.adscoop.userservice.congfig.TokenService;
import com.adscoop.userservice.congfig.UserModel;
import com.adscoop.userservice.handlers.utils.ContextBuilder;
import com.adscoop.userservice.handlers.utils.MethodBuilder;
import com.adscoop.userservice.handlers.utils.RequestBuilder;
import com.adscoop.userservice.handlers.utils.ResponseBuilder;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;

import ratpack.http.HttpMethod;
import ratpack.handling.Context;
import ratpack.http.Request;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class CreateUserHandlerTest {

	@Mock
	private CreateUserHandler handler;
	
	@Mock
	private TokenService generateToken;
	
	private UserNodeServiceImpl userNodeService;
	
	@Before
	public void setUp() {
		handler = new CreateUserHandler(userNodeService, generateToken);
	}
	
	@Test
	public void givenAnExistenEmailWillFaile() throws Exception {
		// given
		Context ctx = ContextBuilder.aBuilder()
				.withRequest(RequestBuilder.aBuilder()
						.withMetod(MethodBuilder.aBuilder()
								.asPost()))
				.withResponse(ResponseBuilder.aBuilder())
				.parse(new UserModel())
				.build();
		
		// when
		handler.handle(ctx);
		
		// then
		verify(ctx.getResponse()).status(eq(409));
	}

}
