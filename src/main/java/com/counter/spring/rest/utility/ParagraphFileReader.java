package com.counter.spring.rest.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class ParagraphFileReader {

	public static Map<String, Integer> fetchParagraphWithCount(String fileName) {

		String text = null;
		Map<String, Integer> textMap = new SortByValueMap<>();
		int count = 0;
		ClassLoader classLoader = ParagraphFileReader.class.getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		try (BufferedReader bufferedInput = new BufferedReader(new FileReader(
				file))) {

			while ((text = bufferedInput.readLine()) != null) {

				text = text.replaceAll("[^a-zA-Z0-9]+", " ");
				Scanner scanner = new Scanner(text);

				while (scanner.hasNext()) {
					String nextText = scanner.next();
					nextText = nextText.trim().toLowerCase();
					if (textMap.containsKey(nextText)) {
						count = textMap.get(nextText);
						textMap.put(nextText, ++count);
					} else {
						textMap.put(nextText, 1);
					}
				}
				scanner.close();
			}

		} catch (IOException e) {

			System.out.println(e.getMessage());
		}

		return textMap;
	}

}
