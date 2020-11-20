package pe.gob.minsa.microservicio.search;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static pe.gob.minsa.microservicio.constants.QueryConstants.*;
import static java.lang.Boolean.parseBoolean;
import static java.time.Instant.parse;

@NoArgsConstructor
@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {
	private static final long serialVersionUID = 1L;
	private SearchCriteria criteria;

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();

		switch (criteria.getType()) {
			case NUM_PARAM: {
				if(criteria.getOperation().equals(G_T))
					return criteriaBuilder.gt(root.get(criteria.getKey()), new BigDecimal(criteria.getValue()));
				if(criteria.getOperation().equals(L_T))
					return criteriaBuilder.lt(root.get(criteria.getKey()), new BigDecimal(criteria.getValue()));
				if(criteria.getOperation().equals(G_T_EQUALS))
					return criteriaBuilder.ge(root.get(criteria.getKey()), new BigDecimal(criteria.getValue()));
				if(criteria.getOperation().equals(L_T_EQUALS))
					return criteriaBuilder.le(root.get(criteria.getKey()), new BigDecimal(criteria.getValue()));
				if(criteria.getOperation().equals(EQUALS))
					return criteriaBuilder.equal(root.get(criteria.getKey()), new BigDecimal(criteria.getValue()));
				if(criteria.getOperation().equals(NOT_EQUALS))
					return criteriaBuilder.notEqual(root.get(criteria.getKey()), new BigDecimal(criteria.getValue()));
				if(criteria.getOperation().equals(IN)) {
					In<Object> in = criteriaBuilder.in(root.get(criteria.getKey()));
					for (String string : criteria.getValue().split(COMMA))
						in.value(new BigDecimal(string));
					return in;
				}
				if(criteria.getOperation().equals(NOT_IN)) {
					In<Object> in = criteriaBuilder.in(root.get(criteria.getKey()));
					for (String string : criteria.getValue().split(COMMA))
						in.value(new BigDecimal(string));
					return in.not();
				}
			} return  null;
			case STRING_PARAM: {
				if(criteria.getOperation().equals(EQUALS))
					return criteriaBuilder.equal(root.<String>get(criteria.getKey()), criteria.getValue());
				if(criteria.getOperation().equals(NOT_EQUALS))
					return criteriaBuilder.equal(root.<String>get(criteria.getKey()), format(MATCH, criteria.getValue())).not();
				if(criteria.getOperation().equals(LIKE))
					return criteriaBuilder.like(root.<String>get(criteria.getKey()), format(MATCH, criteria.getValue()));
				if(criteria.getOperation().equals(NOT_LIKE))
					return criteriaBuilder.notLike(root.<String>get(criteria.getKey()), format(MATCH, criteria.getValue()));
				if(criteria.getOperation().equals(MATCH_START))
					return criteriaBuilder.like(root.get(criteria.getKey()), format(MATCH_START, criteria.getValue()));
				if(criteria.getOperation().equals(MATCH_END))
					return criteriaBuilder.like(root.get(criteria.getKey()), format(MATCH_END, criteria.getValue()));
				if(!criteria.getOperation().equals(IN))
					return null;
				In<Object> in = criteriaBuilder.in(root.get(criteria.getKey()));
				for (String string : criteria.getValue().split(COMMA))
					in.value(string);
				return in;
			}
			case BOOLEAN_PARAM: {
				return criteriaBuilder.equal(root.get(criteria.getKey()), parseBoolean(criteria.getValue()));
			}
			case DATE_TIME_PARAM: {
				if (criteria.getOperation().equals(G_T))
					return criteriaBuilder.greaterThan(root.get(criteria.getKey()), parse(criteria.getValue()));
				if (criteria.getOperation().equals(L_T))
					return criteriaBuilder.lessThan(root.get(criteria.getKey()), parse(criteria.getValue()));
				if (criteria.getOperation().equals(EQUALS))
					return criteriaBuilder.equal(root.get(criteria.getKey()), parse(criteria.getValue()));
				if (criteria.getOperation().equals(NOT_EQUALS))
					return criteriaBuilder.notEqual(root.get(criteria.getKey()), parse(criteria.getValue()));
				if (criteria.getOperation().equals(G_T_EQUALS))
					return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), parse(criteria.getValue()));
				if (criteria.getOperation().equals(L_T_EQUALS))
					return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), parse(criteria.getValue()));
				if (criteria.getOperation().equals(IN)) {
					In<Object> in = criteriaBuilder.in(root.get(criteria.getKey()));
					for (String string : criteria.getValue().split(COMMA))
						in.value(parse(string));
					return in;
				}
			} return null;
			default: return null;
		}
	}
}
