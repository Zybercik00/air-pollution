package ch.kchmiel.airpollution.repository;

import ch.kchmiel.airpollution.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Subscriber findByEmail(String email);
}
