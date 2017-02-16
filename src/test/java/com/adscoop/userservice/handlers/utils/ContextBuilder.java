package com.adscoop.userservice.handlers.utils;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import ratpack.exec.Promise;
import ratpack.func.Action;
import ratpack.handling.Context;
import ratpack.http.Request;
import ratpack.http.Response;

public class ContextBuilder {

	private RequestBuilder requestBuilder = RequestBuilder.aBuilder();
	private ResponseBuilder responseBuilder = ResponseBuilder.aBuilder();
	private Object parseValue;

	public static ContextBuilder aBuilder() {
		return new ContextBuilder();
	}

	public ContextBuilder withRequest(RequestBuilder requestBuilder) {
		this.requestBuilder = requestBuilder;
		return this;
	}

	public ContextBuilder withResponse(ResponseBuilder responseBuilder) {
		this.responseBuilder = responseBuilder;
		return this;
	}

	public ContextBuilder parse(Object value) {
		this.parseValue = value;
		return this;
	}

	public Context build() {
		Context context = mock(Context.class);
		Request request = requestBuilder.build();
		doReturn(request).when(context).getRequest();
		Response response = responseBuilder.build();
		doReturn(response).when(context).getResponse();
		Promise<?> promise = mock(Promise.class);
		Action<?> action = mock(Action.class);
//		doReturn(this.parseValue).when(action).
//		doReturn(action).when(promise).then(any(Action.class));
		doReturn(promise).when(context).parse(any(Class.class));
		return context;
	}

}
