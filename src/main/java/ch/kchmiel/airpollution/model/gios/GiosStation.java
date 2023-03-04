package ch.kchmiel.airpollution.model.gios;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GiosStation {

    private int id;
    private String stationName;
    private GiosCity city;
}
