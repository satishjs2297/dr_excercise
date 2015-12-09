/**
 * 
 */
package com.drmtx.app.demo.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.drmtx.app.TaskRunnerApp;
import com.drmtx.app.demo.model.WordFrequency;

/**
 * @author Accolite
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=TaskRunnerApp.class)
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
