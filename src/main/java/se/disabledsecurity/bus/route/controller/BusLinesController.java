package se.disabledsecurity.bus.route.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.disabledsecurity.bus.route.model.internal.FrontEndModel;
import se.disabledsecurity.bus.route.service.CommuterInformationService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class BusLinesController {

	private final CommuterInformationService commuterInformationService;

	public BusLinesController(CommuterInformationService commuterInformationService) {
		this.commuterInformationService = commuterInformationService;
	}

	@GetMapping(value = "lines")
	public List<FrontEndModel> findLinesWithMostStops() {
		return commuterInformationService
				.findBusLinesWithMostStops(10);
	}
}
