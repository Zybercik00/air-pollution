package ch.kchmiel.airpollution.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "SUBSCRIBERS")
@Getter
@Setter
@NoArgsConstructor
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "subscriber")
    private List<Subscription> subscriptions;

    public Subscriber(String email) {
        this.email = email;
        this.subscriptions = new ArrayList<>();
    }
}
