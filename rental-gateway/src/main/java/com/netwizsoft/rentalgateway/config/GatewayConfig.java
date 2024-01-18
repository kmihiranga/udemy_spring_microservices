package com.netwizsoft.rentalgateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@Configuration
public class GatewayConfig {
	
	@Value("${spring.baseUrl}")
	private String baseUrl;
	
	@Bean
	public WebClient webClient() {
		HttpClient httpClient = HttpClient.create();
		
		ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
		return WebClient.builder()
				.clientConnector(connector)
				.baseUrl(baseUrl)
				.build();
	}
}
