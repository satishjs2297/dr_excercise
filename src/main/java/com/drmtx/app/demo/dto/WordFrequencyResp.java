/**
 * 
 */
package com.drmtx.app.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Accolite
 *
 */
@JsonIgnoreProperties
public class WordFrequencyResp implements Serializable {
	
	private String word;

	private Integer count;
	
	public WordFrequencyResp() {
		// TODO Auto-generated constructor stub
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
