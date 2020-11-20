package pe.gob.minsa.microservicio.search;

import lombok.Data;
import org.springframework.util.StringUtils;
import pe.gob.minsa.microservicio.enums.SearchParamTypeEnum;

@Data
public class SearchCriteria {
	private Boolean clauseAnd;
	private SearchParamTypeEnum type;
	private String key;
	private String operation;
	private String value;

	public SearchCriteria(String clause, String type, String key, String operation, String value) {
		this.clauseAnd = StringUtils.isEmpty(clause);
		this.type = SearchParamTypeEnum.valueOfType(type);
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
}
