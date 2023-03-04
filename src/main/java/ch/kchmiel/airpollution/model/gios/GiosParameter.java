package ch.kchmiel.airpollution.model.gios;

import lombok.*;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GiosParameter {

    private int idParam;
    private String paramName;
    private String paramFormula;
    private String paramCode;
}
