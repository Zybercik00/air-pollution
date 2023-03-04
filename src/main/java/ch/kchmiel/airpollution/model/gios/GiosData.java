package ch.kchmiel.airpollution.model.gios;

import lombok.*;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GiosData {

    private String key;
    private GiosDataEntry[] values;
}
