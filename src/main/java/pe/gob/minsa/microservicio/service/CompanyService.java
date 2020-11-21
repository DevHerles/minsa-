package pe.gob.minsa.microservicio.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.gob.minsa.microservicio.dto.CompanyDto;
import pe.gob.minsa.microservicio.model.Company;
import pe.gob.minsa.microservicio.repository.CompanyRepository;
import pe.gob.minsa.microservicio.search.SearchRequestDto;
import pe.gob.minsa.microservicio.search.SearchResponseDto;
import pe.gob.minsa.microservicio.search.SearchUtil;

import java.util.function.Function;

import static org.springframework.data.domain.Sort.by;
import static pe.gob.minsa.microservicio.constants.QueryConstants.DEFAULT_SORT;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public SearchResponseDto findAll(SearchRequestDto request) {
        Sort sort = by(SearchUtil.getOrders(request.getSorts(), DEFAULT_SORT));
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);
        Page<Company> companies = companyRepository.findAll(SearchUtil.createSpec(request.getQuery()), pageable);
        Function<Company, CompanyDto> mapper = (company) -> SearchUtil.createCopyObject(company, CompanyDto::new);
        return SearchUtil.searchResultResponse(companies, mapper);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    public void delete(Company company) {
        companyRepository.delete(company);
    }

    public void deleteAll() {
        companyRepository.deleteAll();
    }

}
