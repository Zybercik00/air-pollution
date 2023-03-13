package ch.kchmiel.airpollution.service;

import ch.kchmiel.airpollution.model.PollutionReport;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AirPollutionCache {

    private final AirPollutionService service;

    private PollutionReport report;

    public PollutionReport getReport() {
        if (report == null) {
            report = service.getReport();
        }
        return report;
    }

    public void setReport(PollutionReport report) {

        this.report = report;
    }

}
