package com.restfulapi.test;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.restfulapi.http.fluent.APIRequest;

/**
 * 
 * 用百度地图坐标转换API来测试 Restful API 测试框架
 * http://developer.baidu.com/map/index.php?title=webapi/guide/changeposition 
 * 
 * @author Carl Ji
 *
 */
public class BaiduAPITest {

	private String uri = "http://api.map.baidu.com/geoconv/v1/";
	
	private String token = "=";
	
	private String body = "";
	
	private String expectedBody;
	
	@Test
	public void test_GetRequest()
	{
		APIRequest.GET(uri).param("coords", "114.21892734521,29.575429778924;114.21892734521,29.575429778924")
		          .param("from", "1").param("to", "5").invoke().assertStatus(200).assertBodyContains("status");
	}
	
	@Test
	public void test_PutRequest()
	{
		APIRequest.PUT("")
		.type(MediaType.APPLICATION_XML_TYPE).header("Authorization", "Bearer " + token ).body(body).invoke().assertStatus(204);
	}
}
