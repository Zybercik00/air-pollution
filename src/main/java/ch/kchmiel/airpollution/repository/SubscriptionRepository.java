package ch.kchmiel.airpollution.repository;

import ch.kchmiel.airpollution.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
