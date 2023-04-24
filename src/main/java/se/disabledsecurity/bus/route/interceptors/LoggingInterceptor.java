package se.disabledsecurity.bus.route.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {
	private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
	private final AtomicInteger requestNumberSequence = new AtomicInteger(0);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		ClientHttpResponse response = execution.execute(request, body);
		int requestNumber = requestNumberSequence.incrementAndGet();
		logRequest(requestNumber, request, body);
		response = new BufferedClientHttpResponse(response);
		logResponse(requestNumber, response);
		return response;
	}

	private void logRequest(int requestNumber, HttpRequest request, byte[] body) {
		String prefix = requestNumber + " > ";
		log.info("{} Request: {} {}", prefix, request.getMethod(), request.getURI());
		log.info("{} Headers: {}", prefix, request.getHeaders());
		if (body.length > 0) {
			log.info("{} Body: \n{}", prefix, new String(body, StandardCharsets.UTF_8));
		}

	}

	private void logResponse(int requestNumber, ClientHttpResponse response) throws IOException {
		String prefix = requestNumber + " < ";
		log.info("{} Response: {} {}",
				 prefix,
				 response.getStatusCode(),
				 response.getStatusText());
		log.info("{} Headers: {}", prefix, response.getHeaders());
		String body = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
		if (body.length() > 0) {
			log.info("{} Body: \n{}", prefix, body);
		}
	}

	/**
	 * Wrapper around ClientHttpResponse, buffers the body so it can be read repeatedly (for logging & consuming the result).
	 */
	private static class BufferedClientHttpResponse implements ClientHttpResponse {
		private final ClientHttpResponse response;
		private byte[] body;

		BufferedClientHttpResponse(ClientHttpResponse response) {
			this.response = response;
		}

		@Override
		public HttpStatusCode getStatusCode() throws IOException {
			return response.getStatusCode();
		}

		@Deprecated
		@Override
		public int getRawStatusCode() throws IOException {
			return response.getStatusCode().value();
		}

		@Override
		public String getStatusText() throws IOException {
			return response.getStatusText();
		}

		@Override
		public void close() {
			response.close();
		}

		@Override
		public InputStream getBody() throws IOException {
			if (body == null) {
				body = StreamUtils.copyToByteArray(response.getBody());
			}
			return new ByteArrayInputStream(body);
		}

		@Override
		public HttpHeaders getHeaders() {
			return response.getHeaders();
		}
	}
}
