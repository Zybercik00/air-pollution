package ch.kchmiel.airpollution.controller;

import ch.kchmiel.airpollution.model.gios.GiosCity;
import ch.kchmiel.airpollution.model.PollutionReport;
import ch.kchmiel.airpollution.service.AirPollutionCache;
import ch.kchmiel.airpollution.service.AirPollutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1/pollution")
@RequiredArgsConstructor
public class AirPollutionController {

    private final AirPollutionService service;
    private final AirPollutionCache cache;

    @GetMapping("/cities")
    public List<GiosCity> getCities() {
        return service.getCities();
    }

    @GetMapping("/report")
    public PollutionReport getReport() {
        return cache.getReport();
    }



}
