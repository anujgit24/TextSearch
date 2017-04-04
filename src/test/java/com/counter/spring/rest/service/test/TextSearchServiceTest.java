package com.counter.spring.rest.service.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.counter.spring.rest.service.TextSearchService;
import com.counter.spring.test.config.AbstractSpringJunitConfiguration;

public class TextSearchServiceTest extends AbstractSpringJunitConfiguration {
	
	@Autowired
	TextSearchService textSearchService;
	
	@Test
	public void testSearchTextAllValidValue() {
		final String searchtext = "{\"searchText\":[\"Duis\", \"Sed\",\"Donec\",\"Augue\",\"Pellentesque\"]}";
		Map<String,Integer> textWithCount = textSearchService.searchText(searchtext);
		assertEquals("Do not match.","{Duis=11, Sed=16, Donec=8, Augue=7, Pellentesque=6}", textWithCount.toString());
	}
	
	@Test
	public void testSearchTextOneInvalidValue() {
        final String searchtext = "{\"searchText\":[\"Duis\", \"Sed\", \"Donec\",\"123\"]}";
		Map<String,Integer> textWithCount = textSearchService.searchText(searchtext);
		assertEquals("Do not match.","{Duis=11, Sed=16, Donec=8, 123=0}", textWithCount.toString());
	}
	
	@Test
	public void testTopNTextWithNEqualToThree() {
		int topN = 3;
		String csvFormatTopCount = textSearchService.topNText(topN);
		assertEquals("Do not match.","vel|17 eget|17 sed|16 ", csvFormatTopCount);
	}
	
	@Test
	public void testTopNTextWithNEqualToZero() {
		int topN = 0;
		String csvFormatTopCount = textSearchService.topNText(topN);
		assertEquals("Do not match.","", csvFormatTopCount);
	}
	
	@Test
	public void testTopNTextWithNGreaterThanNumberOfUniqueNames() {
		int topN = 1000;
		String csvFormatTopCount = textSearchService.topNText(topN);
		assertEquals("Do not match.","Count is too high, try to reduce it.", csvFormatTopCount);
	}

}
