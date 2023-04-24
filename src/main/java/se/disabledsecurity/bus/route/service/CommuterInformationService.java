package se.disabledsecurity.bus.route.service;

import se.disabledsecurity.bus.route.model.external.Line;

import java.util.List;

public interface CommuterInformationService {
	List<Line> findBusLinesWithMostStops(int numberOfBusLines);
}
