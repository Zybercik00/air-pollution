package ch.kchmiel.airpollution.model.gios;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GiosSensor {

    private int id;
    private int stationId;
    @JsonProperty("param")
    private GiosParameter giosParameter;
}
