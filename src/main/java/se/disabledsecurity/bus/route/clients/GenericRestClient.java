package se.disabledsecurity.bus.route.clients;

import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

public abstract class GenericRestClient {
	protected final RestTemplate restTemplate;
	protected String baseUrl;

	protected GenericRestClient(Logger log, RestTemplate restTemplate, String url ) {
		this.restTemplate = restTemplate;
		this.baseUrl = url;

		log.info("{} pointing at url: {}", this.getClass(), url);
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}
}
