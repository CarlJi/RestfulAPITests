package com.restfulapi.http.fluent;

import javax.ws.rs.core.Response;


import static org.junit.Assert.assertEquals;

public class APIResponse {

	private Response response;

	public APIResponse(Response response)
	{
		this.response = response;
	}

	public APIResponse assertStatus(int status)
	{
		assertEquals(status, response.getStatus());
		return this;
	}

}
