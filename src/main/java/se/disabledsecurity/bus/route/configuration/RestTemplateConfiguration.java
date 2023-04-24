package se.disabledsecurity.bus.route.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import se.disabledsecurity.bus.route.interceptors.LoggingInterceptor;

import java.util.List;

@Configuration
public class RestTemplateConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder()
				.requestFactory(HttpComponentsClientHttpRequestFactory.class)
				.additionalInterceptors(List.of(new LoggingInterceptor()))
				.build();
	}
}
