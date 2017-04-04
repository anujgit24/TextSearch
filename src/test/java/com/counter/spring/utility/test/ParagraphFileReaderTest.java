package com.counter.spring.utility.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.counter.spring.rest.utility.ParagraphFileReader;
import com.counter.spring.test.config.AbstractSpringJunitConfiguration;


public class ParagraphFileReaderTest {
	
	private static final String fileName = "SampleParagraphTest.txt";
		
	@Test
	public void testFetchParagraphWithCount() {
		
		Map<String,Integer> allText = ParagraphFileReader.fetchParagraphWithCount(fileName);
		assertEquals("{a=3, d=2, y=1, t=1, r=1, f=1, c=1, b=1}", allText.toString());
	
	}

}
