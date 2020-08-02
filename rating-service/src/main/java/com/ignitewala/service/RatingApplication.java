package com.ignitewala.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class RatingApplication {
	
	@Value("${max.user.rating}")
	private Integer max = 0;
	
	@Value("${min.user.rating}")
	private Integer min = 0;
	
	@Value("${rating.fetch.limit}")
	private Integer maxLimit = 0;
	

	public static void main(String[] args) {
		SpringApplication.run(RatingApplication.class, args);
	}

	@RequestMapping(value = "/ratings/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> ratings() {
		Map<String, Double> rating = new HashMap<>();
		Random random = new Random();
		IntStream.rangeClosed(1, random.nextInt(maxLimit)).forEach(i -> {
			rating.put(RandomStringUtils.randomAlphabetic(i*5), new Double(Math.max(min,random.nextInt(max))));
		});
		return new ResponseEntity<Map<String, Double>>(rating, HttpStatus.OK);
	}

	@RequestMapping(value = "/ratings/tutor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> tutor() {
		Map<String, Double> rating = new HashMap<>();
		Random random = new Random();
		IntStream.rangeClosed(1, random.nextInt(maxLimit)).forEach(i -> {
			rating.put(RandomStringUtils.randomAlphabetic(i*5), new Double(Math.max(min,random.nextInt(max))));
		});
		return new ResponseEntity<Map<String, Double>>(rating, HttpStatus.OK);
	}
}
