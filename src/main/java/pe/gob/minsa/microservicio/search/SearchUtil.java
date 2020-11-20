package pe.gob.minsa.microservicio.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;

import static pe.gob.minsa.microservicio.constants.QueryConstants.*;
import static java.util.stream.Collectors.toList;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

public final class SearchUtil {

    private SearchUtil() {
    }

    public static List<Sort.Order> getOrders(List<String> sorts, String defaultProp) {
        if (sorts.isEmpty())
            return Arrays.asList(new Sort.Order(DESC, defaultProp));
        List<Sort.Order> orders = new ArrayList<Sort.Order>(sorts.size());
        for (String sort : sorts) {
            String[] split = sort.split(COLON);
            orders.add(new Sort.Order(split.length > 1 ? ASC : DESC, split[0]));
        }
        return orders;
    }

    public static <T> Specification<T> createSpec(String query) {
        SearchSpecificationBuilder<T> builder = new SearchSpecificationBuilder<T>();
        for (Matcher matcher = SEARCH_QUERY_PATTERN.matcher(query + COMMA); matcher.find();)
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4),
                    matcher.group(5).replaceAll(DOUBLE_QUOTES, EMPTY));
        return builder.build();
    }

    public static <T, R> SearchResponseDto searchResultResponse(Page<T> page, Function<T, R> mapper) {
        SearchResponseDto response = new SearchResponseDto();
        response.setData(page.stream().map(mapper).collect(toList()));
        response.setPageIndex(page.getNumber());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setLast(page.isLast());
        response.setSize(page.getSize());
        response.setNumber(page.getNumber());
        response.setSort(page.getSort());
        response.setFirst(page.isFirst());
        response.setNumberOfElements(page.getNumberOfElements());
        response.setEmpty(page.isEmpty());
        return response;
    }

    public static <T> T createCopyObject(Object src, Supplier<T> supplier) {
        T dest = supplier.get();
        copyProperties(src, dest);
        return dest;
    }
}
