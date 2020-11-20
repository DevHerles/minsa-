package pe.gob.minsa.microservicio.search;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
public class SearchResponseDto {
    private List<Object> data;
    private int totalPages;
    private int pageIndex;
    private Long totalElements;
    private Boolean last;
    private int size;
    private int number;
    private Boolean first;
    private int numberOfElements;
    private Boolean empty;
    private Sort sort;
}
