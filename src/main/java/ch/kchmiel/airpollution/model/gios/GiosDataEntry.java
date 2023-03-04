package ch.kchmiel.airpollution.model.gios;

import lombok.*;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GiosDataEntry {

    private String date;
    private double value;

}
