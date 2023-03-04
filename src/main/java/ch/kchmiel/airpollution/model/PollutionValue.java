package ch.kchmiel.airpollution.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class PollutionValue {

    private Double value;

    private QualityLevel level;
}
