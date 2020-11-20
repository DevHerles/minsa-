package pe.gob.minsa.microservicio.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data @AllArgsConstructor @NoArgsConstructor
public class RequestParamCriteria {
    @NotBlank
    private String query;

    @Min(0)
    private int offset = 0;

    @Min(1)
    private int limit = 10;
}