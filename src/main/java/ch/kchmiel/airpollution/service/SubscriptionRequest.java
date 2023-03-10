package ch.kchmiel.airpollution.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SubscriptionRequest {
    private String email;
    private String city;
}
