package com.counter.spring.rest.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.counter.spring.rest.service.TextSearchService;
import com.counter.spring.rest.utility.ParagraphFileReader;

@Service
public class TextSearchServiceImpl implements TextSearchService {

	private static final String FILE_NAME = "SampleParagraph.txt";

	private static final Integer COUNT_ZERO = 0;

	static Map<String, Integer> allText;

	static {
		allText = ParagraphFileReader.fetchParagraphWithCount(FILE_NAME);
	}

	public Map<String, Integer> searchText(String searchText) {

		List<String> textList = convertStringtoList(searchText);
		Map<String, Integer> textWithCount = new LinkedHashMap<>();

		for (String inputText : textList) {
			String trimText = inputText.trim().toLowerCase();
			if (allText.containsKey(trimText)) {
				textWithCount.put(inputText.trim(), allText.get(trimText));
			} else {
				textWithCount.put(inputText.trim(), COUNT_ZERO);
			}
		}

		return textWithCount;
	}

	public String topNText(int count) {

		StringBuilder topNText = new StringBuilder();
		Set<Entry<String, Integer>> entries = allText.entrySet();

		if ((count <= entries.size())) {
			for (Entry<String, Integer> entry : entries) {

				if (count-- != 0) {
					topNText.append(entry.getKey() + "|" + entry.getValue()
							+ " ");
				} else {
					break;
				}
			}
		} else {
			topNText.append("Count is too high, try to reduce it.");
		}
		return topNText.toString();
	}

	private List<String> convertStringtoList(String text) {

		String[] textArr = text.split(":");
		List<String> textList = new ArrayList<>();
		if (textArr.length == 2) {
			text = textArr[1].replaceAll("[^a-zA-Z0-9]+", " ");
			Scanner scanner = new Scanner(text);

			while (scanner.hasNext()) {
				textList.add(scanner.next());

			}
			scanner.close();
		}

		return textList;
	}

}
