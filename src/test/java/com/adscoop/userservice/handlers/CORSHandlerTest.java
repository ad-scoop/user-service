package com.adscoop.userservice.handlers;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ratpack.handling.Context;
import ratpack.http.MutableHeaders;
import ratpack.http.Response;

@RunWith(MockitoJUnitRunner.class)
public class CORSHandlerTest {

	@Mock
	private Context context;

	@Mock
	private Response response;
	
	@Mock
	private MutableHeaders headers;

	private CORSHandler corsHandler = new CORSHandler();
	
	@Before
	public void setUp() {
		doReturn(response).when(context).getResponse();
		doReturn(headers).when(response).getHeaders();
	}
	
	@Test
	public void verifyThatTheHeaderAreSetOnRespose() throws Exception {
		// given when
		corsHandler.handle(context);
		
		// then
		verify(headers).set(eq(CORSHandler.ACCESS_CONTROL_ALLOW_ORIGIN), eq(CORSHandler.ACCESS_CONTROL_ALLOW_ORIGIN_VALUE));
		verify(headers).set(eq(CORSHandler.ACCESS_CONTROL_ALLOW_HEADERS), eq(CORSHandler.ACCESS_CONTROL_ALLOW_HEADERS_VALUE));
	}
	
	@Test
	public void verifyThatTheChainIsCalled() throws Exception {
		// given when
		corsHandler.handle(context);
		
		// then
		verify(context).next();
	}
	
	
}
