package ch.kchmiel.airpollution.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class PollutionReport {

    private List<PollutionReportEntry> entries;
}
