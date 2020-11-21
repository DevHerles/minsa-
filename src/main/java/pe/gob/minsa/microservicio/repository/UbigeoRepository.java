package pe.gob.minsa.microservicio.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.gob.minsa.microservicio.model.Ubigeo;

import java.util.List;

public interface UbigeoRepository extends PagingAndSortingRepository<Ubigeo, Long>, JpaSpecificationExecutor<Ubigeo> {
    @Query("select u from Ubigeo u where u.id=:id")
    Ubigeo findByCompany(Long id);
}
