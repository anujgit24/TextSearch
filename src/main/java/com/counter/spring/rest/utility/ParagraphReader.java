package com.counter.spring.rest.utility;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface ParagraphReader {

	public Map<String,Integer> fetchParagraphWithCount(String fileName);

}
