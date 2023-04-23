package se.disabledsecurity.bus.route.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiBaseModel<T>(
		@JsonProperty("StatusCode") int statusCode,
		@JsonProperty("Message") String message,
		@JsonProperty("ExecutionTime") int executionTime,
		@JsonProperty("ResponseData") ApiData<T> responseData) {}
