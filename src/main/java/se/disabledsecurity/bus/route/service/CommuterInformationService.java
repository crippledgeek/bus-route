package se.disabledsecurity.bus.route.service;

import se.disabledsecurity.bus.route.model.internal.FrontEndModel;

import java.util.List;

public interface CommuterInformationService {
	List<FrontEndModel> findBusLinesWithMostStops(int numberOfBusLines);
}
