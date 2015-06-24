package com.restfulapi.test;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.restfulapi.http.fluent.APIRequest;

public class TestAPI {

	private String URL = "";
	
	private String token = "=";
	
	private String body = "";
	
	@Test
	public void test_GetRequest()
	{
		String token = "bnVsbDoxMTI3YmQ3YTZlNzYxMDJkOTJiMDEyMzEzZHBkYTZtZDpQQU06MTQzNDc4ODQzOTY1MToxNDM4MjQ0NDM5NjUxOjI6ZmFsc2U6SnROQnRhVDNvNVp0dzdqd2xvMkM0TFpsSEZzQlkrb0xWRm5rY0Q2WVUwbmNPc1J1SVNvcExnb0VzV2NoRkY5OUd6bk1ha1JEYTVLWFVBbVZNeEQ3YklTVStIV2doay9mNFZvUjJsS2FTUlBQZU9KNVFzdDFYdjBKTEs4bDJtbGhFWWR6UmJDcmt6K3l1eTJnNCtwVDByM1BleG1MYVlzaUtUangwSHladWlCTTZwNzRHcS9tWkxqLysxRk9xRGliNEpkcGo2TUc1YVZPSzkyWWh0cGlzR0hoa1lPWnFkbFA0dUVUZ0hEZ2dWQVgwU1QxdkdVTmhORG1mQ0o5RWVnM2R6U0UrTFU0V2NHRWw1TE9hb0xyaGs2NTF0V3VwYlExT1RlSDV3VWtLY1ZTblltT1hyWm9JaWg1QnRrdHMvTFNPSnFEN2JvSkJ4YnhuRzM5TXFkTXJRPT0=";
		APIRequest.GET(URL).header("Authorization", "Bearer " + token).invoke().assertStatus(200);
	}
	
	@Test
	public void test_PutRequest()
	{
		APIRequest.PUT("")
		.type(MediaType.APPLICATION_XML_TYPE).header("Authorization", "Bearer " + token ).body(body).invoke().assertStatus(204);
	}
}
