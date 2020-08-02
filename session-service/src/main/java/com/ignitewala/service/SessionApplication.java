package com.ignitewala.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.lang.RandomStringUtils;
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
public class SessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionApplication.class, args);
	}

	@RequestMapping(value = "/session/upcoming", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, List<String>>> upcomingsessions() {
		List<String> sessions = new ArrayList<String>();
		IntStream.rangeClosed(1, new Random().nextInt(20)).forEach(i -> {
			sessions.add(RandomStringUtils.randomAlphabetic(i));
		});
		Map<String, List<String>> sessionMap = new HashMap<>();
		sessionMap.put("sessions", sessions);
		return new ResponseEntity<Map<String, List<String>>>(sessionMap, HttpStatus.OK);
	}
}
