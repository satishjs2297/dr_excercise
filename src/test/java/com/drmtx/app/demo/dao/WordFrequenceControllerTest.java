package com.drmtx.app.demo.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.drmtx.app.demo.controller.WordFrequenceController;
import com.drmtx.app.demo.dto.WordFrequencyResp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-jpa-config.xml"})
public class WordFrequenceControllerTest {

	@Autowired
	private WordFrequenceController wordFrequenceController;
	
	@Before
	public void setUP() {
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	
	@Test
	public void testPersistWordFreq() {
		String urlInput = "https://www.reddit.com/r/java/comments/32pj67/java_reference_in_gta_v_beautiful/.json";
		String persistedId = wordFrequenceController.commentsReader(urlInput);
	}
	
	@Test
	@Ignore
	public void testGetWordFrequency() {
		List<WordFrequencyResp> wordFreqLst = wordFrequenceController.getWordFrequency(0l, 4);
		Assert.notNull(wordFreqLst);
		System.out.println(wordFreqLst);
	}
	
}
