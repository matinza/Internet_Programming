package com.hit.test;

import org.junit.Test;

import com.hit.algorithm.KmpSearchAlgorithm;
import com.hit.algorithm.NaiveSearchAlgorithm;

import org.junit.Assert;

public class SearchAlgorithmTest {

	@Test
	public void getPatternByIndexInNaiveAlgo() {
		String txt = "AABAACAADAABAAABAA";
		String pat = "AABA";

		var naiveAlgo = new NaiveSearchAlgorithm();

		var result = naiveAlgo.IndexArrayOfPatternInText(pat, txt);
		System.out.println("1->" + result);
		Assert.assertEquals((int) result.get(0), 0);
		Assert.assertEquals((int) result.get(1), 9);
		Assert.assertEquals((int) result.get(2), 13);
	}

	@Test
	public void getPatternInEmptyTextNaiveAlgoReturnEmptyResult() {
		String txt = "";
		String pat = "AABA";

		var naiveAlgo = new NaiveSearchAlgorithm();

		var result = naiveAlgo.IndexArrayOfPatternInText(pat, txt);
		System.out.println("2->" + result);
		Assert.assertEquals(result.size(), 0);
	}

	@Test
	public void getPatternByIndexInKmpAlgo() {
		String txt = "AABAACAADAABAAABAA";
		String pat = "AABA";

		var kmpAlgo = new KmpSearchAlgorithm();

		var result = kmpAlgo.IndexArrayOfPatternInText(pat, txt);
		System.out.println("3->" + result);
		Assert.assertEquals((int) result.get(0), 0);
		Assert.assertEquals((int) result.get(1), 9);
		Assert.assertEquals((int) result.get(2), 13);
	}

	@Test
	public void getPatternInTextKmpAlgoReturnEmptyResult() {
		String txt = "";
		String pat = "AABA";

		var kmpAlgo = new KmpSearchAlgorithm();

		var result = kmpAlgo.IndexArrayOfPatternInText(pat, txt);
		System.out.println("4->" + result);
		Assert.assertEquals(result.size(), 0);
	}
}
