package com.counter.spring.rest.service;

import java.util.Map;


public interface TextSearchService {
	
	public Map<String,Integer> searchText(String searchText);
		
	public String topNText(int count);
}
