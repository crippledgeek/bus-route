package se.disabledsecurity.bus.route.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Line(

		@JsonProperty("LineNumber") int lineNumber,
		@JsonProperty("LineDesignation") String lineDesignation,
		@JsonProperty("DefaultTransportMode") String defaultTransportMode,
		@JsonProperty("DefaultTransportModeCode") String defaultTransportModeCode,
		@JsonProperty("LastModifiedUtcDateTime")String lastModifiedUtcDateTime,
		@JsonProperty("ExistsFromDate")String existsFromDate
){}
