package ch.kchmiel.airpollution.scheduler;

import ch.kchmiel.airpollution.model.*;
import ch.kchmiel.airpollution.repository.SubscriptionRepository;
import ch.kchmiel.airpollution.service.AirPollutionCache;
import ch.kchmiel.airpollution.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class AirPollutionSubscriptionScheduler {

    private final AirPollutionCache cache;
    private final SubscriptionRepository repository;
    private final SimpleEmailService service;

    @Scheduled(cron = "16 0 * * * *")
    public void sendEmails() {
        PollutionReport report = cache.getReport();
        List<Subscription> subscriptions = repository.findAll();
        Map<Subscriber, List<Subscription>> subscriberSubscriptions = subscriptions.stream().collect(Collectors.groupingBy(x -> x.getSubscriber()));
        subscriberSubscriptions.forEach((subscriber, subs) -> {
            List<PollutionReportEntry> reportEntries = subs.stream().map(s -> s.getCity()).map(city -> report.get(city)).toList();
            Mail mail = createMail(subscriber, reportEntries);
            service.send(mail);
        });
    }

    private Mail createMail(Subscriber subscriber, List<PollutionReportEntry> entries) {
        String message = createMessage(entries);
        return new Mail(subscriber.getEmail(), "Air Pollution Report", message);
    }

    private String createMessage(List<PollutionReportEntry> entries) {
        return entries.stream()
                .map(entry -> entry.getCityName() + ": " + entry.getIndex().name() + ", PM10: " + entry.getValues().get("PM10") + ", PM2.5: " + entry.getValues().get("PM2.5")).collect(Collectors.joining("\n"));
    }

}
