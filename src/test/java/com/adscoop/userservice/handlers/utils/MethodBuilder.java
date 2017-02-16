package com.adscoop.userservice.handlers.utils;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import ratpack.http.HttpMethod;

public class MethodBuilder {

	private boolean post;

	public static MethodBuilder aBuilder() {
		return new MethodBuilder();
	}

	public MethodBuilder asPost() {
		this.post = true;
		return this;
	}

	public HttpMethod builder() {
		HttpMethod method = mock(HttpMethod.class);
		doReturn(post).when(method).isPost();
		return method;
	}

}
