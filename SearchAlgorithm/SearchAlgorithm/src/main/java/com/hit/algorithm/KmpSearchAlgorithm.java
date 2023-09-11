package com.hit.algorithm;

import java.util.ArrayList;
import java.util.List;

public class KmpSearchAlgorithm implements IAlgoSearch {

	@Override
	public List<Integer> IndexArrayOfPatternInText(String pattern, String text) {
		return KMPSearch(text, pattern);
	}

	public List<Integer> KMPSearch(String text, String pattern) {
		List<Integer> indexes = new ArrayList<Integer>();

		// base case 1: pattern is null or empty
		if (pattern == null || pattern.length() == 0) {
//			System.out.println("The pattern occurs with shift 0");
			return List.of();
		}

		// base case 2: text is NULL, or text's length is less than that of pattern's
		if (text == null || pattern.length() > text.length()) {
//			System.out.println("Pattern not found");
			return List.of();
		}

		char[] chars = pattern.toCharArray();

		// next[i] stores the index of the next best partial match
		int[] next = new int[pattern.length() + 1];
		for (int i = 1; i < pattern.length(); i++) {
			int j = next[i];

			while (j > 0 && chars[j] != chars[i]) {
				j = next[j];
			}

			if (j > 0 || chars[j] == chars[i]) {
				next[i + 1] = j + 1;
			}
		}

		for (int i = 0, j = 0; i < text.length(); i++) {
			if (j < pattern.length() && text.charAt(i) == pattern.charAt(j)) {
				if (++j == pattern.length()) {
//					System.out.println("The pattern occurs with shift " + (i - j + 1));
					indexes.add(i - j + 1);
				}
			} else if (j > 0) {
				j = next[j];
				i--; // since `i` will be incremented in the next iteration
			}
		}
		return indexes;
	}
}