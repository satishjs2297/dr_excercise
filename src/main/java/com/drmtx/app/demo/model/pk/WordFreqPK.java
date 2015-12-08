/**
 * 
 */
package com.drmtx.app.demo.model.pk;

import java.io.Serializable;

/**
 * @author Accolite
 *
 */
public class WordFreqPK implements Serializable{
	
	private Long wId;
	
	private String word;
	
	public WordFreqPK() {
		// TODO Auto-generated constructor stub
	}

	public WordFreqPK(Long wId, String word) {
		super();
		this.wId = wId;
		this.word = word;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		WordFreqPK other = (WordFreqPK) obj;
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

	

}
