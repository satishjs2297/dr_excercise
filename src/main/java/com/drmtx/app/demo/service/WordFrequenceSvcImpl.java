/**
 * 
 */
package com.drmtx.app.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.drmtx.app.demo.dao.WordFrequenceDAO;
import com.drmtx.app.demo.dto.RedditJSONConstants;
import com.drmtx.app.demo.dto.WordFrequencyResp;
import com.drmtx.app.demo.model.WordFrequency;

/**
 * @author Accolite
 *
 */
@Service
public class WordFrequenceSvcImpl implements WordFrequenceSvc {

	private static final Logger LOG = LoggerFactory.getLogger(WordFrequenceSvc.class);

	@Autowired
	private WordFrequenceDAO wordFrequenceDAO;

	public WordFrequenceSvcImpl() {

	}

	@Override
	public Long persistWordFrequence(List<Map> respArryLst) {

		long strTime = System.currentTimeMillis();
		long duration1 = 0l, duration2 = 01;

		LOG.debug(">>>>>>>>> respArryLst  ::  {}", respArryLst);
		List<String> bodyContentLst = new ArrayList<String>();

		for (Map<String, Object> respMap : respArryLst) {
			recursiveBodySearch(respMap, bodyContentLst);
		}

		Map<String, Integer> wordFrequencyMap = calcualteWordFrequency(bodyContentLst);
		LOG.info("wordFrequencyMap :: {}  ", wordFrequencyMap);

		Long maxWordId = wordFrequenceDAO.getMaxWordId();

		List<WordFrequency> wordFreLst = new ArrayList<WordFrequency>();
		maxWordId++;
		for (String key : wordFrequencyMap.keySet()) {
			wordFreLst.add(new WordFrequency(maxWordId, key, wordFrequencyMap.get(key)));
		}

		LOG.debug("***********************************************************");
		duration1 = System.currentTimeMillis() - strTime;
		LOG.info("*****************Data Manipulation TT ****************  :: {}", duration1);

		Long persistedWId = wordFrequenceDAO.persistBatchWordsFrequency(wordFreLst);

		duration2 = System.currentTimeMillis() - strTime;
		duration2 = duration2 - duration1;
		LOG.info("*****************Data Persistence TT ****************  :: {}", duration2);
		LOG.info("persistedWId :: {}", persistedWId);

		return persistedWId;
	}

	@Override
	public List<WordFrequencyResp> getWordFrequencyById(Long id, Integer count) {

		List<WordFrequency> wordFrequencyLst = wordFrequenceDAO.getWordFrequencyById(id, count);

		LOG.info("wordFrequencyLst :: {}", wordFrequencyLst);
		List<WordFrequencyResp> wordFreqRespLst = new ArrayList<WordFrequencyResp>();
		for (WordFrequency wordFreq : wordFrequencyLst) {
			WordFrequencyResp wordFreqResp = new WordFrequencyResp();
			BeanUtils.copyProperties(wordFreq, wordFreqResp);
			wordFreqRespLst.add(wordFreqResp);
		}
		wordFrequencyLst.clear();

		return wordFreqRespLst;
	}

	/**
	 * RecursiveBody Search
	 * 
	 * @param respMap
	 * @param bodyContentLst
	 */
	@SuppressWarnings("unchecked")
	public void recursiveBodySearch(Map<String, Object> respMap, List<String> bodyContentLst) {

		if (respMap.containsKey(RedditJSONConstants.DATA)) {

			Map<String, Object> dataMap = (Map<String, Object>) respMap.get(RedditJSONConstants.DATA);

			if (dataMap == null || dataMap.isEmpty())
				return;

			if (dataMap.containsKey(RedditJSONConstants.CHILDREN)) {

				List<Map<String, Object>> childrenLstMap = (List<Map<String, Object>>) dataMap
						.get(RedditJSONConstants.CHILDREN);
				for (Map<String, Object> childerMap : childrenLstMap) {

					if (childerMap.containsKey(RedditJSONConstants.DATA)) {

						Map<String, Object> childDataMap = (Map<String, Object>) childerMap
								.get(RedditJSONConstants.DATA);
						if (childDataMap.containsKey(RedditJSONConstants.BODY)) {
							bodyContentLst.add(formatBodyContent((String) childDataMap.get(RedditJSONConstants.BODY)));
						}

						if (childDataMap.containsKey(RedditJSONConstants.REPLIES)) {

							Object replies = childDataMap.get(RedditJSONConstants.REPLIES);
							if (replies instanceof Map) {

								Map<String, Object> repliesDataMap = (Map<String, Object>) childDataMap
										.get(RedditJSONConstants.REPLIES);
								recursiveBodySearch(repliesDataMap, bodyContentLst);
							}

						}
					}

				}
			}

		}
	}

	/**
	 * Formate to : Lower case sentence. Removes While space and special
	 * characters
	 * 
	 * @param inputBody
	 * @return
	 */
	public String formatBodyContent(String inputBody) {
		String formatedContent = "";
		if (inputBody != null && !"".equals(inputBody)) {
			inputBody = inputBody.toLowerCase();
			inputBody = StringUtils.trimWhitespace(inputBody);
			inputBody = inputBody.replaceAll("[^a-zA-Z0-9]", " ");
			formatedContent = inputBody;
		}
		return formatedContent;
	}

	/**
	 * 
	 * @param bodyContentLst
	 * @return
	 */
	public Map<String, Integer> calcualteWordFrequency(List<String> bodyContentLst) {

		Map<String, Integer> wordFrequencyMap = new HashMap<String, Integer>();

		for (String bodyContent : bodyContentLst) {
			String[] bodyWords = bodyContent.split("\\s+");
			for (String word : bodyWords) {
				Integer wordCounter = 0;
				if (!wordFrequencyMap.containsKey(word)) {
					wordCounter = 1;
				} else {
					wordCounter = wordFrequencyMap.get(word);
					wordCounter++;
				}
				wordFrequencyMap.put(word, wordCounter);
			}
		}

		return wordFrequencyMap;
	}

}
