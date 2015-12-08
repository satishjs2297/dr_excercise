/**
 * 
 */
package com.drmtx.app.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author Accolite
 *
 */
@IdClass(com.drmtx.app.demo.model.pk.WordFreqPK.class)
@Entity
@Table(name = "WORDFREQUENCY")
public class WordFrequency implements Serializable {

	@Id
	private Long wId;

	@Id
	private String word;

	private Integer count;

	public WordFrequency() {
		// TODO Auto-generated constructor stub
	}

	public WordFrequency(Long wId, String word, Integer count) {
		super();
		this.wId = wId;
		this.word = word;
		this.count = count;
	}


	public Long getwId() {
		return wId;
	}

	public void setwId(Long wId) {
		this.wId = wId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((wId == null) ? 0 : wId.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordFrequency other = (WordFrequency) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (wId == null) {
			if (other.wId != null)
				return false;
		} else if (!wId.equals(other.wId))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WordFrequency [wId=" + wId + ", word=" + word + ", count=" + count + "]";
	}

}
