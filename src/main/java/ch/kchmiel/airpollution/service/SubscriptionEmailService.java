package ch.kchmiel.airpollution.service;

import ch.kchmiel.airpollution.model.Subscriber;
import ch.kchmiel.airpollution.model.Subscription;
import ch.kchmiel.airpollution.repository.SubscriberRepository;
import ch.kchmiel.airpollution.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionEmailService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriberRepository subscriberRepository;

    public void subscribe(String email, String city) {
        Subscriber subscriber = subscriberRepository.findByEmail(email);
        if (subscriber == null) {
            subscriber = new Subscriber(email);
            subscriberRepository.save(subscriber);
        }
        subscriptionRepository.save(new Subscription(subscriber, city));
    }

}
