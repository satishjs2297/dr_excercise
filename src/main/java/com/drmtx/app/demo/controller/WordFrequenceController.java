package com.drmtx.app.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.drmtx.app.demo.dto.WordFrequencyResp;
import com.drmtx.app.demo.service.WordFrequenceSvc;

@RestController
@RequestMapping(value = "/frequency")
public class WordFrequenceController {

	private static final Logger LOG = LoggerFactory.getLogger(WordFrequenceController.class);

	@Autowired
	private WordFrequenceSvc wordFrequenceSvc;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String commentsReader(@RequestParam(value = "inputUrl", required = true) String inputUrl) {

		LOG.debug("commentsReader#Begin :: inputUrl :: {}", inputUrl);
		Long persistedId = 0l;
		long strTime = System.currentTimeMillis();
		long duration1 = 0l, duration2 = 0l;
		try {

			URI uri = new URI(inputUrl);
			ResponseEntity<Object> responseEntity = restTemplate.getForEntity(uri, Object.class);

			LOG.info(">>>>>>>>> response code ::  {}", responseEntity.getStatusCode());
			List<Map> respArryLst = (List<Map>) responseEntity.getBody();
			duration1 = System.currentTimeMillis() - strTime;

			long strTime1 = System.currentTimeMillis();
			persistedId = wordFrequenceSvc.persistWordFrequence(respArryLst);
			duration2 = System.currentTimeMillis() - strTime1;
		} catch (URISyntaxException e) {
			LOG.error("commentsReader @ Error :: {}", e.getMessage());
		}

		return "Generated Id :: " + persistedId + "  Total Time :: " + (System.currentTimeMillis() - strTime)
				+ ", DB insert time:" + duration2 + ", Rest Resp Time :: " + duration1;
	}

	@RequestMapping(value = "/{id}")
	public List<WordFrequencyResp> getWordFrequency(@PathVariable("id") Long id,
			@RequestParam(value = "count") Integer count) {
		List<WordFrequencyResp> wordFrequencyById = null;
		try {
			LOG.debug(" id :: {} and count :: {} ", id, count);
			wordFrequencyById = wordFrequenceSvc.getWordFrequencyById(id, count);

			LOG.info("wordFrequencyById :: {}", wordFrequencyById);

		} catch (Exception ex) {
			LOG.error("getWordFrequency @ Error :: {}", ex.getMessage());
		}

		return wordFrequencyById;
	}

}
