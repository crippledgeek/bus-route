package se.disabledsecurity.bus.route.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StopPoint(
		@JsonProperty("StopPointNumber") int stopPointNumber,
		@JsonProperty("StopPointName") String stopPointName,
		@JsonProperty("StopAreaNumber") int stopAreaNumber,
		@JsonProperty("LocationNorthingCoordinate") String locationNorthingCoordinate,
		@JsonProperty("LocationEastingCoordinate") String locationEastingCoordinate,
		@JsonProperty("ZoneShortName") String zoneShortName,
		@JsonProperty("StopAreaTypeCode") String stopAreaTypeCode,
		@JsonProperty("LastModifiedUtcDateTime") String lastModifiedUtcDateTime,
		@JsonProperty("ExistsFromDate") String existsFromDate) {
}
