package se.disabledsecurity.bus.route.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Route (
		@JsonProperty("LineNumber") int lineNumber,
		@JsonProperty("DirectionCode") String directionCode,
		@JsonProperty("JourneyPatternPointNumber") int journeyPatternPointNumber,
		@JsonProperty("LastModifiedUtcDateTime") String lastModifiedUtcDateTime,
		@JsonProperty("ExistsFromDate") String existsFromDate) {}
