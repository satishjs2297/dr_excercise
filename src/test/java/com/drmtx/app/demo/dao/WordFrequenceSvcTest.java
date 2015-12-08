package com.drmtx.app.demo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.json.JacksonJsonParser;

public class WordFrequenceSvcTest {
	
	private List<Object> parseList;

	@Before
	public void setUP() {

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		try (BufferedReader bReader = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "/arc-response-2015 Dec 8 11-54-33.json"));) {
			String str = null;
			StringBuilder stBuilder = new StringBuilder();
			while ((str = bReader.readLine()) != null) {
				stBuilder.append(str);
			}
			String json = stBuilder.toString();
			System.out.println("json>>> ::" + json);
			parseList = jsonParser.parseList(json);
			System.out.println("parseList ::: " + parseList);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testRecursiveBodySearch() throws IOException {
		
		List<String> bodyContentLst = new ArrayList<String>();
		for (Object respObj : parseList) {
			Map<String,Object> respMap = (Map<String,Object>) respObj;
			//recursiveBodySearch(respMap, bodyContentLst);
		}

	}
}
