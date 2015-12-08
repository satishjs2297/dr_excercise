/**
 * 
 */
package com.drmtx.app.demo.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.drmtx.app.demo.model.WordFrequency;

import org.junit.Assert;

/**
 * @author Accolite
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-jpa-config.xml"})
public class WordFrequenceDAOTest {
	
	@Autowired
	private WordFrequenceDAO wordFrequenceDAO;
	
	
	private WordFrequency wordFreq;
	
	@Before
	public void setUP() {
		wordFreq = new WordFrequency(1l, "ABC", 1);
	}
	
	@After
	public void tearDown() {
		
	}
	
	
	@Test
	@Ignore
	public void testPersistwordFrequence() {
		
		wordFrequenceDAO.persistWordsFrequency(Arrays.asList(wordFreq));
	}
	
	@Test
	public void testGetWordFrequence() {
		Long maxWordId = wordFrequenceDAO.getMaxWordId();
		System.out.println("maxWordId :: "+maxWordId);
		Assert.assertNotNull(maxWordId);
		List<WordFrequency> wordFrequencyById = wordFrequenceDAO.getWordFrequencyById(maxWordId, 1);
		System.out.println("wordFrequencyById::: "+wordFrequencyById);
	}
	
	

}
