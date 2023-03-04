package ch.kchmiel.airpollution.model.gios;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GiosIndex {

    private int id;
    private StationIndexLevel stIndexLevel;

}
