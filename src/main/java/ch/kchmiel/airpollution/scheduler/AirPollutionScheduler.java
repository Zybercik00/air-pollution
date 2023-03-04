package ch.kchmiel.airpollution.scheduler;

import ch.kchmiel.airpollution.model.PollutionReport;
import ch.kchmiel.airpollution.service.AirPollutionCache;
import ch.kchmiel.airpollution.service.AirPollutionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AirPollutionScheduler {

    private final AirPollutionCache cache;
    private final AirPollutionService service;

    @Scheduled(cron = "* 5 * * * *")
    public void getReport() {
        // wez najnowszy raport
        log.info("Loading new report from Gios");
        PollutionReport report = service.getReport();
        // wrzuc go do kesza
        log.info("New report loaded");
        cache.setReport(report);
    }
}

