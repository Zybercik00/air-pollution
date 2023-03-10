package ch.kchmiel.airpollution.controller;

import ch.kchmiel.airpollution.model.Subscriber;
import ch.kchmiel.airpollution.repository.SubscriberRepository;
import ch.kchmiel.airpollution.service.SubscriptionEmailService;
import ch.kchmiel.airpollution.service.SubscriptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionEmailService subscriptionEmailService;
    private final SubscriberRepository subscriberRepository;


    @PostMapping("/subscribe")
    public ResponseEntity<List<Subscriber>> subscribe(@RequestBody SubscriptionRequest request) {
        subscriptionEmailService.subscribe(request.getEmail(), request.getCity());
        return ResponseEntity.ok().build();
    }

}
