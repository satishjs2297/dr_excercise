/**
 * 
 */
package com.drmtx.app.demo.service;

import java.util.List;
import java.util.Map;

import com.drmtx.app.demo.dto.WordFrequencyResp;

/**
 * @author Accolite
 *
 */
public interface WordFrequenceSvc {
	
	
	public Long persistWordFrequence(List<Map> respArryLst);
	
	
	public List<WordFrequencyResp> getWordFrequencyById(Long id, Integer count);
	
	

}
