/**
 * 
 */
package com.drmtx.app.demo.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.drmtx.app.demo.model.WordFrequency;

/**
 * @author Accolite
 *
 */
@Repository
public class WordFrequenceDAOImpl implements WordFrequenceDAO {

	@PersistenceContext(unitName = "ACCO_DBUNIT")
	private EntityManager eManager;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${BATCHSIZE}")
	private Integer batchSize;

	public WordFrequenceDAOImpl() {

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Long persistWordsFrequency(List<WordFrequency> wordFreqLst) {

		long wid = 0;
		int count = 0;
		for (WordFrequency wordFeq : wordFreqLst) {
			wid = wordFeq.getwId();
			eManager.persist(wordFeq);
			count++;
			if (count % batchSize == 0) {
				eManager.flush();
				eManager.clear();
			}
		}
		return wid;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long persistBatchWordsFrequency(List<WordFrequency> wordFreqLst) {

		if(wordFreqLst == null)
			throw new IllegalArgumentException("wordFreqLst is null");
		
		int wordFreqSize = wordFreqLst.size();
		final long wid = wordFreqSize > 0 ? wordFreqLst.get(0).getwId() : -1;

		for (int i = 0; i < wordFreqSize; i += batchSize) {

			final List<WordFrequency> batchList = wordFreqLst.subList(i,
					i + batchSize > wordFreqSize ? wordFreqSize : i + batchSize);

			jdbcTemplate.batchUpdate(WordFrequenceDAO.SQL_WORD_FREQUENCY_INSERT, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement pStmt, int j) throws SQLException {
					WordFrequency wordFrequency = batchList.get(j);
					pStmt.setLong(1, wordFrequency.getwId());
					pStmt.setString(2, wordFrequency.getWord());
					pStmt.setInt(3, wordFrequency.getCount());

				}

				@Override
				public int getBatchSize() {
					return batchList.size();
				}
			});

		}

		return wid;
	}

	@Override
	public Long getMaxWordId() {
		BigInteger maxId = (BigInteger) eManager.createNativeQuery(WordFrequenceDAO.MAX_WID).getSingleResult();
		return maxId != null ? maxId.longValue() : -1;
	}

	@Override
	public List<WordFrequency> getWordFrequencyById(Long id, Integer count) {

		return eManager.createQuery(WordFrequenceDAO.QUERY_WORDS_BYID).setParameter("wid", id).setMaxResults(count)
				.getResultList();
	}

}
