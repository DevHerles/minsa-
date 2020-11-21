package pe.gob.minsa.microservicio.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.gob.minsa.microservicio.model.Support;

import java.util.List;

public interface SupportRepository extends PagingAndSortingRepository<Support, Long>, JpaSpecificationExecutor<Support> {
    @Query("select s from Support s where s.company.id=:id")
    List<Support> findByCompanyId(Long id);
}
