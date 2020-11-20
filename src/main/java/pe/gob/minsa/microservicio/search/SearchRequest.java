package pe.gob.minsa.microservicio.search;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static pe.gob.minsa.microservicio.constants.QueryConstants.DEFAULT_PAGE_NUMBER;
import static pe.gob.minsa.microservicio.constants.QueryConstants.DEFAULT_PAGE_SIZE;

@Data
public class SearchRequest {
    private int pageIndex = DEFAULT_PAGE_NUMBER;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private String query;
    private List<String> sorts = new ArrayList<String>();
}
