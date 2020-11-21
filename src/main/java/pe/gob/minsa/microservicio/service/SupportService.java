package pe.gob.minsa.microservicio.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.gob.minsa.microservicio.dto.SupportDto;
import pe.gob.minsa.microservicio.model.Support;
import pe.gob.minsa.microservicio.repository.SupportRepository;
import pe.gob.minsa.microservicio.search.SearchRequestDto;
import pe.gob.minsa.microservicio.search.SearchResponseDto;
import pe.gob.minsa.microservicio.search.SearchUtil;

import java.util.List;
import java.util.function.Function;

import static org.springframework.data.domain.Sort.by;
import static pe.gob.minsa.microservicio.constants.QueryConstants.DEFAULT_SORT;

@Service
@AllArgsConstructor
public class SupportService {
    private final SupportRepository supportRepository;

    public SearchResponseDto findAll(SearchRequestDto request) {
        Sort sort = by(SearchUtil.getOrders(request.getSorts(), DEFAULT_SORT));
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);
        Page<Support> supports = supportRepository.findAll(SearchUtil.createSpec(request.getQuery()), pageable);
        Function<Support, SupportDto> mapper = (faq) -> SearchUtil.createCopyObject(faq, SupportDto::new);
        return SearchUtil.searchResultResponse(supports, mapper);
    }

    public Support save(Support faq) {
        return supportRepository.save(faq);
    }

    public Support findById(Long id) {
        return supportRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        supportRepository.deleteById(id);
    }

    public void delete(Support faq) {
        supportRepository.delete(faq);
    }

    public List<Support> findByCompanyId(Long id) {
        return supportRepository.findByCompanyId(id);
    }
}
