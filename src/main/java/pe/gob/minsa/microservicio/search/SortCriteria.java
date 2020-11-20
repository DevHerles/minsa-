package pe.gob.minsa.microservicio.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor
public class SortCriteria {
    @NotNull
    private SortOrder order;

    @NotBlank
    private String sortAttribute;
}
