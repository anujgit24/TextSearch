package com.counter.spring.rest.controller.test;


import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.counter.spring.rest.service.TextSearchService;
import com.counter.spring.test.config.AbstractSecurityJunitConfiguration;


@WebAppConfiguration
public class TextSearchControllerTest extends AbstractSecurityJunitConfiguration {
	
	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext WebApplicationContext;
	
	@Mock
	TextSearchService textSearchService;
	
	@Before
	public void setUp(){
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
        .webAppContextSetup(WebApplicationContext)
        .apply(springSecurity())
        .build();
		
	} 
	
	@Test
	public void testSearchTextPostRequestAuthorized() throws Exception{
		String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("anuj:password").getBytes()));
		this.mockMvc.perform(post("/counter-api/search")
				.header("Authorization", basicDigestHeaderValue)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{\"searchText\" : [\"Duis\", \"Sed\", \"Donec\"]}"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testSearchTextPostRequestUnAuthorized() throws Exception{
		this.mockMvc.perform(post("/counter-api/search")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{\"searchText\" : [\"Duis\", \"Sed\", \"Donec\"]}"))
				.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void testSearchTopNCountTextGetRequestAuthorized() throws Exception{
		String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("anuj:password").getBytes()));
		this.mockMvc.perform(get("/counter-api/count/3")
				.header("Authorization", basicDigestHeaderValue)
				.contentType(MediaType.ALL_VALUE))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testSearchTopNCountTextGetRequestUnAuthorized() throws Exception{
		this.mockMvc.perform(get("/counter-api/count/3")
				.contentType(MediaType.ALL_VALUE))
				.andExpect(status().isUnauthorized());
	}
}
