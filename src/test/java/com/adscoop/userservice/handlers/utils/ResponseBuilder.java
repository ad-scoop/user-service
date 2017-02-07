package com.adscoop.userservice.handlers.utils;

import static org.mockito.Mockito.mock;

import ratpack.http.Response;

public class ResponseBuilder {

	public static ResponseBuilder aBuilder() {
		return new ResponseBuilder();
	}

	public Response build() {
		return mock(Response.class);
	}

}
