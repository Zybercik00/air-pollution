package ch.kchmiel.airpollution.service;

import ch.kchmiel.airpollution.client.GiosClient;
import ch.kchmiel.airpollution.model.PollutionReportEntry;
import ch.kchmiel.airpollution.model.QualityLevel;
import ch.kchmiel.airpollution.model.gios.*;
import ch.kchmiel.airpollution.model.PollutionReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirPollutionService {

    private static final String PM10 = "PM10";
    private static final String PM2_5 = "PM2.5";
    private static final Set<String> PARAMETERS = Set.of(PM10, PM2_5);


    private final GiosClient client;

    public List<GiosCity> getCities() {
        return client.getGiosStations().stream()
                .map(GiosStation::getCity)
                .distinct()
                .sorted(Comparator.comparing(GiosCity::getName))
                .collect(Collectors.toList());
    }

    public PollutionReport getReport() {
        return new PollutionReport(getCities().stream()
                .map(city -> getReport(city.getId()))
                .collect(Collectors.toList()));
    }

    private String getCityName(int cityId) {
        return getCities().stream().filter(city -> city.getId() == cityId).findFirst().map(city -> city.getName()).orElseThrow(() -> new IllegalStateException("City with id=" + cityId + " not found"));
    }

    public PollutionReportEntry getReport(int cityId) {
        String cityName = getCityName(cityId);
        List<Integer> stationIds = client.getGiosStations().stream().filter(station -> station.getCity().getId() == cityId).map(GiosStation::getId).toList();

        QualityLevel index = getQualityLevel(stationIds);
        List<Integer> sensorIds = stationIds.stream().flatMap(stationId -> client.getGiosSensors(stationId).stream()).filter(sensor -> PARAMETERS.contains(sensor.getGiosParameter().getParamCode())).map(GiosSensor::getId).toList();
        List<GiosData> dataFromSensors = sensorIds.stream().map(client::getGiosData).toList();
        Map<String, List<GiosData>> dataFromSensorsGrouped = dataFromSensors.stream().collect(Collectors.groupingBy(v -> v.getKey()));
        Map<String, Double> singleAverageSensorData = dataFromSensorsGrouped.entrySet().stream().collect(Collectors.toMap(x -> x.getKey(), x -> getAverage(x.getValue())));
        return new PollutionReportEntry(cityName, index, singleAverageSensorData);
    }

    private double getLastNotZero(GiosData data) {
        return Arrays.stream(data.getValues()).map(val -> val.getValue()).filter(val -> val > 0).findFirst().orElse(0.0);
    }

    private double getAverage(List<GiosData> data) {
        return data.stream().map(d -> getLastNotZero(d)).mapToDouble(x -> x).average().orElse(0.0);
    }

    private QualityLevel getQualityLevel(List<Integer> stationIds) {
        double avg = stationIds.stream().map(client::getGiosIndex).map(GiosIndex::getStIndexLevel).mapToInt(StationIndexLevel::getId).filter(val -> val != -1).average().orElse(-1);
        int roundedAvg = (int) Math.round(avg);
        return QualityLevel.getByIndex(roundedAvg);
    }
}
