package ch.kchmiel.airpollution.client;

import ch.kchmiel.airpollution.model.gios.GiosData;
import ch.kchmiel.airpollution.model.gios.GiosIndex;
import ch.kchmiel.airpollution.model.gios.GiosSensor;
import ch.kchmiel.airpollution.model.gios.GiosStation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;


@Service
@RequiredArgsConstructor
public class GiosClient {

    private final RestTemplate restTemplate;

    private final GiosConfig giosConfig;

    public List<GiosStation> getGiosStations() {
        URI url = UriComponentsBuilder.fromUriString(giosConfig.getGiosStationsEndpoint()).build().toUri();
        GiosStation[] stations = restTemplate.getForObject(url, GiosStation[].class);
        return new ArrayList<>(Optional.ofNullable(stations).map(Arrays::asList).orElseGet(Collections::emptyList));
    }

    public List<GiosSensor> getGiosSensors(int stationId) {
        Map<String, Integer> urlParams = new HashMap<>();
        urlParams.put("stationId", stationId);
        URI url = UriComponentsBuilder.fromUriString(giosConfig.getGiosSensorsEndpoint()).buildAndExpand(urlParams).toUri();
        GiosSensor[] sensors = restTemplate.getForObject(url, GiosSensor[].class);
        return new ArrayList<>(Optional.ofNullable(sensors).map(Arrays::asList).orElseGet(Collections::emptyList));
    }

    public GiosData getGiosData(int sensorId) {
        Map<String, Integer> urlParams = new HashMap<>();
        urlParams.put("sensorId", sensorId);
        URI url = UriComponentsBuilder.fromUriString(giosConfig.getGiosSensorDataEndpoint()).buildAndExpand(urlParams).toUri();
        GiosData data = restTemplate.getForObject(url, GiosData.class);
        return data;

    }

    public GiosIndex getGiosIndex(int stationId) {
        Map<String, Integer> urlParams = new HashMap<>();
        urlParams.put("stationId", stationId);
        URI url = UriComponentsBuilder.fromUriString(giosConfig.getGiosIndexEndpoint()).buildAndExpand(urlParams).toUri();
        GiosIndex index = restTemplate.getForObject(url, GiosIndex.class);
        return index;

    }

}