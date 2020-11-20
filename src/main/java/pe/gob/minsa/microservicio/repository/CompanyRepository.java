package pe.gob.minsa.microservicio.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.gob.minsa.microservicio.model.Company;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long>, JpaSpecificationExecutor<Company> {
}
