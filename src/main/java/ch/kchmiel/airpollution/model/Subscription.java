package ch.kchmiel.airpollution.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SUBSCRIPTIONS")
@Getter
@Setter
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subsriber_id")
    private Subscriber subscriber;

    public Subscription(Subscriber subscriber, String city) {
        this.subscriber = subscriber;
        this.city = city;
    }

}
