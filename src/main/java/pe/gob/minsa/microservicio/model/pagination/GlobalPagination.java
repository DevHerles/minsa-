package pe.gob.minsa.microservicio.model.pagination;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pe.gob.minsa.microservicio.constants.QueryConstants;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"pageNumber", "pageSize", "query"}, allowGetters = true)
public class GlobalPagination {
    private int pageNumber = QueryConstants.DEFAULT_PAGE_NUMBER;
    private int pageSize = QueryConstants.DEFAULT_PAGE_SIZE;
    private String query;
    private List<String> sorts = new ArrayList<String>();
}