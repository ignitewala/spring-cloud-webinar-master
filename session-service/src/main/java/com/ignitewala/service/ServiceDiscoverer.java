package com.ignitewala.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/session-client")
public class ServiceDiscoverer {

	@Autowired
	private LoadBalancerClient loadBalancer;

	@GetMapping("/upcoming")
	public ResponseEntity<Map> upcomingsessions() {
		ServiceInstance instance = loadBalancer.choose("SESSION-SERVICE");

		String baseUrl = instance.getUri().toString();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> response = null;
		try {
			response = restTemplate.exchange(baseUrl + "/session/upcoming", HttpMethod.GET, getHeaders(), Map.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
        System.out.println(response.getBody());
		return response;
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
