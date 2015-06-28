package com.restfulapi.test;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.jcj.rest.general.helper.APITest;
import com.jcj.rest.http.fluent.APIRequest;

/**
 * 
 * Demo about how to use this framework
 * 
 * Need store some some APIs configuration in a env.properties files
 * 
 * @author Carl Ji
 *
 */
public class HTTPRequestsTest extends APITest {
	
	@Test
	public void testHttpGETReguest()
	{
		String uri = getValue("get.uri");
		String token = getValue("get.token");
		
	    APIRequest.GET(uri).header("Authorization", "Bearer " + token)
	    .invoke().assertStatus(200).assertBodyContains("778959e1051f1e1b45558b5e1677607116aa2758fd8e26894cb04c72700e95de");
	}
	
	@Test
	public void testHttpPOSTReguest()
	{
		String uri = getValue("post.uri");
		String token = getValue("post.token");
		String payload = String.format(loadFile("jsonfile.json"), "62ab92bab49692ea813c99b3d2b4ebbdb3aa99ffede992762b5edc612f162814", "f57405304f15cba90c214252f85b925407704d3bb67b598bb49ada0460161b63");
			
	    APIRequest.POST(uri).header("Authorization", "Bearer " + token).type(MediaType.APPLICATION_JSON_TYPE).body(payload)
	    .invoke().assertStatus(409).assertBodyContains("User already part of device group");
	}
	
	@Test
	public void testHttpPUTReguest()
	{
		String uri = getValue("put.uri");
		String token = getValue("put.token");
		String payload = loadFile("xmlfile.xml");
		
		
	    APIRequest.PUT(uri).header("Authorization", "Bearer " + token).type(MediaType.APPLICATION_XML_TYPE).body(payload)
	    .invoke().assertStatus(204);
	}
	
	@Test
	public void testHttpDELETEReguest()
	{
		String uri = getValue("delete.uri");;
		String token = getValue("delete.token");; 
		String payload = String.format(loadFile("jsonfile2.json"), "1783a8eb18109f7f6386c4187e42e06c4ca32f8b2c0101ce3a281dcc06edec8e","029c14ffe730dd784b2eff76badc54ab516b4ec0fedb12f35e01894b4916a7fd");
		
	    APIRequest.DELETE(uri).header("Authorization", "Bearer " + token).type(MediaType.APPLICATION_JSON_TYPE).body(payload)
	    .invoke().assertStatus(200);
	}

}
