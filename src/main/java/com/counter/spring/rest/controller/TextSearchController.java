package com.counter.spring.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.counter.spring.rest.service.TextSearchService;

@Component
@RestController
@RequestMapping(value="/counter-api")
public class TextSearchController {
	
	
	private TextSearchService textSearchService;
	
	@Autowired
	public TextSearchController(TextSearchService textSearchService){
		this.textSearchService = textSearchService;
	}
	
	@RequestMapping(value="/search", method= RequestMethod.POST, consumes="application/json")
	public Map<String,Integer> searchText(@RequestBody String searchText){
		return textSearchService.searchText(searchText);
	}
	
	@RequestMapping(value="/count/{count}", method= RequestMethod.GET, produces="text/csv")
	public String searchTopNCountText(@PathVariable int count){
		
		return textSearchService.topNText(count);
	}
	
	
}
