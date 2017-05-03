package com.adscoop.userservice.handlers;

import static org.mockito.Matchers.anyString;
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
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CORSHandlerTest {

	public static final String ACCESS_CONTROL_ALLOW_HEADERS_VALUE = "x-requested-with, origin, content-type, accept, token";
	public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	public static final String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "*";
	public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

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

		when(headers.get(anyString())).thenReturn(ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);

	HandlingResult handlingResult =

			RequestFixture.handle(new CORSHandler(), requestFixture -> {
			requestFixture.method("POST").uri("*/**");

		});
		assertEquals(ACCESS_CONTROL_ALLOW_HEADERS_VALUE,handlingResult.getHeaders().get(ACCESS_CONTROL_ALLOW_ORIGIN));
	// given when
	//	corsHandler.handle(context);
		
		// then
	//	verify(headers).set(eq(CORSHandler.ACCESS_CONTROL_ALLOW_ORIGIN), eq(CORSHandler.ACCESS_CONTROL_ALLOW_ORIGIN_VALUE));
	//	verify(headers).set(eq(CORSHandler.ACCESS_CONTROL_ALLOW_HEADERS), eq(CORSHandler.ACCESS_CONTROL_ALLOW_HEADERS_VALUE));
	}
	
	@Test
	public void verifyThatTheChainIsCalled() throws Exception {
		// given when
		corsHandler.handle(context);
		
		// then
		verify(context).next();
	}
	
	
}
