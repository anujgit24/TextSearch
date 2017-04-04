package com.counter.spring.utility.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.counter.spring.rest.utility.SortByValueMap;

public class SortByValueMapTest {

	
	SortByValueMap<String,Integer> sortByValueMap;
	
	@Test
	public void testSortingWithNoDuplicateKeysAndValues() {
		
		sortByValueMap = new SortByValueMap<>();
		
		sortByValueMap.put("Anuj",20);
		sortByValueMap.put("Swati",50);
		sortByValueMap.put("Tera",10);
		
		
		assertEquals("{Swati=50, Anuj=20, Tera=10}", sortByValueMap.toString());
		
		
	}
	
	@Test
	public void testSortingWithDuplicateKeys() {
		
		sortByValueMap = new SortByValueMap<>();
		
		sortByValueMap.put("Anuj",20);
		sortByValueMap.put("Swati",50);
		sortByValueMap.put("Tera",10);
		sortByValueMap.put("Tera",30);
		
		assertEquals("{Swati=50, Tera=30, Anuj=20}", sortByValueMap.toString());
		
		
	}
	
	@Test
	public void testSortingWithDuplicateValues() {
		
		sortByValueMap = new SortByValueMap<>();
		sortByValueMap.put("Anuj",20);
		sortByValueMap.put("Swati",50);
		sortByValueMap.put("Tera",10);
		sortByValueMap.put("Tera",20);
		
		assertEquals("{Swati=50, Tera=20, Anuj=20}", sortByValueMap.toString());
		
		
	}

}
