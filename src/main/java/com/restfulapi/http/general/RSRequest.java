package com.restfulapi.http.general;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
 * 
 * @author Carl Ji
 *
 */
public class RSRequest {
	
	private String uri;
	private WebTarget webTarget;

	public RSRequest(String uri)
	{
        
	}

	public void invoke()
	{
		ClientConfig config = new ClientConfig();
		
		//Important: we print all logs for each request and response
		config.register(new LoggingFilter());
		
		Client client = ClientBuilder.newClient(config);
		
		
		WebTarget webTarget = client.target("http://example.com/rest");
		WebTarget resourceWebTarget = webTarget.path("resource");
		WebTarget helloworldWebTarget = resourceWebTarget.path("helloworld");
		WebTarget helloworldWebTargetWithQueryParam =
				helloworldWebTarget.queryParam("greeting", "Hi World!");

		Invocation.Builder invocationBuilder =
				helloworldWebTargetWithQueryParam.request(MediaType.TEXT_PLAIN_TYPE);
		invocationBuilder.header("some-header", "true");
		
		Response response = invocationBuilder.get();
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

}
