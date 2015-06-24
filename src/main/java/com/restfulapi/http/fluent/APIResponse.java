package com.restfulapi.http.fluent;

import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;

/**
 * Help class to check HTTP response
 * 
 * @author Carl Ji
 */
public class APIResponse {

	private Response response;
	private String body;

	public APIResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * Asserts the status of the HTTP response 
	 * 
	 * @param status
	 *        The expected number that will be compared with actual HTTP response status  
	 * @return
	 *        {@link APIResponse}
	 */
	public APIResponse assertStatus(int status)
	{
		assertEquals(status, response.getStatus());
		return this;
	}
	
	/**
	 * 
	 * Assert the body of HTTP response
	 * 
	 * @param content
	 *        The content that will be compared with actual HTTP response body
	 * @return
	 */
	public APIResponse assertBody(String content)
	{
		String body = getBody();
		
		assertEquals(content, body);
		return this;
	}
	
	/**
	 * Assert HTTP response body contains the expected content
	 * 
	 * @param content
	 *        The expected content in HTTP response
	 * @return
	 *        {@link APIResponse}
	 */
	public APIResponse assertBodyContains(String content)
	{
		String body = getBody();
		
		assertEquals(true, body.contains(content));
		return this;
	}
	
	/**
	 * Get the body of HTTP response as String
	 * 
	 * @return the body of HTTP response
	 */
	public String getBody()
	{
		if(body == null)
		{
			return response.readEntity(String.class);
		}
		return body;
	}
	
	/**
	 * Get the body as the given type
	 * 
	 * @param t
	 *        The class type that you want to get
	 * @return
	 *        The response body as given type
	 */
	public <T> T getBody(Class<T> t)
	{
		return response.readEntity(t);
	}
	

}
