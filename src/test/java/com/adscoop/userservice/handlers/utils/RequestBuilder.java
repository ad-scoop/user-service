package com.adscoop.userservice.handlers.utils;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import ratpack.http.HttpMethod;
import ratpack.http.Request;

public class RequestBuilder {

	private MethodBuilder method = MethodBuilder.aBuilder();

	public static RequestBuilder aBuilder() {
		return new RequestBuilder();
	}

	public RequestBuilder withMetod(MethodBuilder method) {
		this.method  = method;
		return this;
	}

	public Request build() {
		Request request = mock(Request.class);
		HttpMethod httpMethod = method.builder();
		doReturn(httpMethod).when(request).getMethod();
		return request;
	}

}
