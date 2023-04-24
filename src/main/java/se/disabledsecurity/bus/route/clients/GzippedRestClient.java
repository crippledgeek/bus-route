package se.disabledsecurity.bus.route.clients;

import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;



public abstract class GzippedRestClient extends GenericRestClient {
	protected GzippedRestClient(Logger log, RestTemplate restTemplate, String url) {
		super(log, restTemplate, url);
	}
	protected HttpHeaders createRequestHeaders() {
		HttpHeaders headers;
		headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate");
		return headers;
	}

	protected <T> HttpEntity<T> createRequest(T request, HttpHeaders headers){
		return new HttpEntity<>(request, headers);
	}

	protected HttpEntity<Object> createRequest(HttpHeaders headers){
		return new HttpEntity<>(headers);
	}
}
