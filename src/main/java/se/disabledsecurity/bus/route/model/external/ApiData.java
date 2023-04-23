package se.disabledsecurity.bus.route.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ApiData<T>(
		@JsonProperty("Version") String version,
		@JsonProperty("Type") String type,
		@JsonProperty("Result") List<T> result) {
}
