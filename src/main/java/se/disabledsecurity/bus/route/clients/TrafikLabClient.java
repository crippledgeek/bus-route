package se.disabledsecurity.bus.route.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import se.disabledsecurity.bus.route.exception.ApiFetchException;
import se.disabledsecurity.bus.route.model.external.ApiBaseModel;
import se.disabledsecurity.bus.route.model.external.Line;
import se.disabledsecurity.bus.route.model.external.Route;
import se.disabledsecurity.bus.route.model.external.StopPoint;

@Slf4j
@Component
public class TrafikLabClient extends GzippedRestClient {
	protected TrafikLabClient(RestTemplate restTemplate, @Value("${TRAFIKLAB_API_URL}") String url) {
		super(log, restTemplate, url);
	}

	public ApiBaseModel<Line> getAllBuses() {
		try {
			return restTemplate
					.exchange(createBusLinesRequestUrl(),
							  HttpMethod.GET,
							  createRequest(null, createRequestHeaders()),
							  new ParameterizedTypeReference<ApiBaseModel<Line>>(){})
					.getBody();

		} catch (RestClientException e) {
			log.error("Could not fetch bus lines from API");
			throw new ApiFetchException(e);
		}
	}

	public ApiBaseModel<StopPoint> getAllBusStops() {
		try {
			return restTemplate
					.exchange(createBusStopsRequestUrl(),
							  HttpMethod.GET,
							  createRequest(null, createRequestHeaders()),
							  new ParameterizedTypeReference<ApiBaseModel<StopPoint>>(){})
					.getBody();

		} catch (RestClientException e) {
			log.error("Could not fetch bus lines from API");
			throw new ApiFetchException(e);
		}
	}

	public ApiBaseModel<Route> getAllBusRoutes() {
		try {
			return restTemplate
					.exchange(createBusRoutesRequestUrl(),
							  HttpMethod.GET,
							  createRequest(null, createRequestHeaders()),
							  new ParameterizedTypeReference<ApiBaseModel<Route>>(){})
					.getBody();

		} catch (RestClientException e) {
			log.error("Could not fetch bus lines from API");
			throw new ApiFetchException(e);
		}
	}

	private String createBusLinesRequestUrl() {
		return UriComponentsBuilder
				.fromHttpUrl(baseUrl)
				.queryParam("model", "line")
				.queryParam("DefaultTransportModeCode", "BUS")
				.build()
				.toUriString();
	}

	private String createBusStopsRequestUrl() {
		return UriComponentsBuilder
				.fromHttpUrl(baseUrl)
				.queryParam("model", "stop")
				.queryParam("StopAreaTypeCode", "BUSTERM")
				.build()
				.toUriString();
	}

	private String createBusRoutesRequestUrl() {
		return UriComponentsBuilder
				.fromHttpUrl(baseUrl)
				.queryParam("model", "jour")
				.queryParam("DefaultTransportModeCode", "BUS")
				.build()
				.toUriString();
	}
}
