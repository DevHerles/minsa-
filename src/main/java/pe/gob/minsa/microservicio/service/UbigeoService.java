package pe.gob.minsa.microservicio.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.gob.minsa.microservicio.dto.UbigeoDto;
import pe.gob.minsa.microservicio.model.Ubigeo;
import pe.gob.minsa.microservicio.repository.UbigeoRepository;
import pe.gob.minsa.microservicio.search.SearchRequestDto;
import pe.gob.minsa.microservicio.search.SearchResponseDto;
import pe.gob.minsa.microservicio.search.SearchUtil;

import java.util.List;
import java.util.function.Function;

import static org.springframework.data.domain.Sort.by;
import static pe.gob.minsa.microservicio.constants.QueryConstants.DEFAULT_SORT;

@Service
@AllArgsConstructor
public class UbigeoService {
    private final UbigeoRepository ubigeoRepository;

    public SearchResponseDto findAll(SearchRequestDto request) {
        Sort sort = by(SearchUtil.getOrders(request.getSorts(), DEFAULT_SORT));
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);
        Page<Ubigeo> ubigeos = ubigeoRepository.findAll(SearchUtil.createSpec(request.getQuery()), pageable);
        Function<Ubigeo, UbigeoDto> mapper = (faq) -> SearchUtil.createCopyObject(faq, UbigeoDto::new);
        return SearchUtil.searchResultResponse(ubigeos, mapper);
    }

    public Ubigeo save(Ubigeo faq) {
        return ubigeoRepository.save(faq);
    }

    public Ubigeo findById(Long id) {
        return ubigeoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        ubigeoRepository.deleteById(id);
    }

    public void delete(Ubigeo faq) {
        ubigeoRepository.delete(faq);
    }

    public Ubigeo findByCompanyId(Long id) {
        return ubigeoRepository.findByCompany(id);
    }
}
