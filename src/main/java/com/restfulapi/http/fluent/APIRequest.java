package com.restfulapi.http.fluent;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;


/**
 * General Class to make HTTP calls
 * 
 * @author Carl Ji
 */
public class APIRequest {

	private UriBuilder uri;
	private Map<String, String> params = new HashMap<String, String>();
	private Map<String, String> headers = new HashMap<String, String>();
	private MediaType contentType = MediaType.APPLICATION_XML_TYPE;
	private MediaType acceptType;
	private String httpMethod;
	private String body;

	private APIRequest(String uri, String method)
	{
		this.uri=UriBuilder.fromUri(uri);
		this.httpMethod = method;
	}

	/**
	 * Build a HTTP Get request
	 * 
	 * @param uri
	 *        The URI on which a HTTP get request will be called
	 * @return
	 *        {@link APIRequest} 
	 */
	public static APIRequest GET(String uri)
	{
		return new APIRequest(uri, HttpMethod.GET);
	}

	/**
	 * Build a HTTP Post request
	 * 
	 * @param uri
	 *        The URI on which a POST request will be called
	 * @return
	 *        {@link APIRequest}
	 */
	public static APIRequest POST(String uri)
	{
		return new APIRequest(uri, HttpMethod.POST);
	}

	/**
	 * Build a HTTP Put request
	 * 
	 * @param uri
	 *        The URI on which a PUT request will be called
	 * @return
	 *        {@link APIRequest}
	 */
	public static APIRequest PUT(String uri)
	{
		return new APIRequest(uri, HttpMethod.PUT);
	}

	/**
	 * Build a HTTP Delete request
	 * 
	 * @param uri
	 *        The URI that the Delete Request will be called
	 * @return
	 *        {@link APIRequest}
	 */
	public static APIRequest DELETE(String uri)
	{
		return new APIRequest(uri, HttpMethod.DELETE);
	}

	/**
	 * Build a HTTP HEAD request
	 * 
	 * @param uri
	 *        The URI that the Head request will be called
	 * @return
	 *        {@link APIRequest}
	 */
	public static APIRequest HEAD(String uri)
	{
		return new APIRequest(uri, HttpMethod.HEAD);
	}

	/**
	 * Add the {@code value} to the end of URI to build the final URI
	 * 
	 * @param value
	 *        The value that will be appended to the URI
	 * @return
	 *        {@link APIRequest}
	 */
	public APIRequest path(String value)
	{
		this.uri.path(value);
		return this;
	}

	/**
	 * Build the parameter in the request URI
	 * 
	 * @param key
	 *        The request URI parameter key
	 * @param value
	 *        The request URI parameter value
	 * @return
	 *        {@link APIRequest}
	 */
	public APIRequest param(String key, String value)
	{
		params.put(key, value);
		return this;
	}

	/**
	 * Set the content type in the request body
	 * 
	 * @param type
	 *        The content type {@link MediaType} 
	 * @return
	 *        {@link APIRequest}
	 */
	public APIRequest type(MediaType type)
	{
		this.contentType = type;
		return this;
	}

	/**
	 * Set the accepted type for the HTTP response when calling the specific HTTP request
	 * 
	 * @param type
	 *        The accepted type for the response of this request
	 * @return
	 *        {@link APIRequest}
	 */
	public APIRequest accept(MediaType type)
	{
		this.acceptType = type;
		return this;
	}

	/**
	 * Set the HTTP request headers parameter
	 * 
	 * @param key
	 *        The header name
	 * @param value
	 *        The corresponding value for the header 
	 * @return
	 *        {@link APIRequest}
	 */
	public APIRequest header(String key, String value)
	{
		headers.put(key, value);
		return this;
	}

	/**
	 * Set the request body
	 * 
	 * @param body
	 *        The body of the request
	 * @return
	 *        {@link APIRequest}
	 */
	public APIRequest body(String body)
	{
		this.body = body;
		return this;
	}

	/**
	 * Invoke jersey client to send HTTP request
	 * 
	 * @return {@link APIResponse}
	 */
	public APIResponse invoke()
	{
		ClientConfig config = new ClientConfig();

		//Important: we print all logs for each request and response
		config.register(new LoggingFilter());

		Client client = ClientBuilder.newClient(config);
		WebTarget webTarget = client.target(uri);
		if(!params.isEmpty())
		{
			for(String key: params.keySet())
			{
				webTarget = webTarget.queryParam(key, params.get(key));
			}
		}

		Invocation.Builder invocationBuilder= webTarget.request();
		if(acceptType != null)
		{
			invocationBuilder = invocationBuilder.accept(acceptType);
		}

		if(!headers.isEmpty())
		{
			for(String key: headers.keySet())
			{
				invocationBuilder.header(key, headers.get(key));
			}
		}
		
		Response response = invocationBuilder.method(httpMethod, Entity.entity(body, contentType), Response.class);
		return new APIResponse(response);
	}

}
