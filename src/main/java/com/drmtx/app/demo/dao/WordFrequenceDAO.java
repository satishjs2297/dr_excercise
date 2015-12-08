/**
 * 
 */
package com.drmtx.app.demo.dao;

import java.util.List;

import com.drmtx.app.demo.model.WordFrequency;

/**
 * @author Accolite
 *
 */
public interface WordFrequenceDAO {
	
	public static final String MAX_WID = "select max(wid) from WORDFREQUENCY";
	
	public static final String QUERY_WORDS_BYID = "select w from WordFrequency w where w.wId=:wid ORDER BY w.count DESC";
	
	public static final String SQL_WORD_FREQUENCY_INSERT = "INSERT INTO WORDFREQUENCY(wId,word,count) values(?,?,?)";
	
	public Long persistWordsFrequency(List<WordFrequency> wordFreqLst);
	
	public Long getMaxWordId();
	
	public List<WordFrequency> getWordFrequencyById(Long id, Integer count);
	
	public Long persistBatchWordsFrequency(List<WordFrequency> wordFreqLst);

}
