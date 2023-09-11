package com.hit.algorithm;

import java.util.ArrayList;
import java.util.List;

public class NaiveSearchAlgorithm implements IAlgoSearch{
	
	@Override
	public List<Integer> IndexArrayOfPatternInText(String pattern, String text) {
		return Search(text, pattern);
	}
	
	private List<Integer> Search(String pattern, String text)
	{
		List<Integer> indexs = new ArrayList<Integer>();
		int l1 = pattern.length();
		int l2 = text.length();
		int i = 0, j = l2 - 1;
		
		for (i = 0, j = l2 - 1; j < l1;) {
		if (text.equals(pattern.substring(i, j + 1))) {
//			System.out.println("Pattern found at index " + i);
			indexs.add(i);
		}
			i++;
			j++;
		}
		
		return indexs;
	}
}
