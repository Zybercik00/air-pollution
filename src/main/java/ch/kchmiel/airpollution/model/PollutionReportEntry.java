package ch.kchmiel.airpollution.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class PollutionReportEntry {

    private String cityName;
    private QualityLevel index;
    private Map<String, Double> values;

}