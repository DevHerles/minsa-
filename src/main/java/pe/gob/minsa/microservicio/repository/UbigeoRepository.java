package pe.gob.minsa.microservicio.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.gob.minsa.microservicio.model.Ubigeo;

public interface UbigeoRepository extends PagingAndSortingRepository<Ubigeo, Long>, JpaSpecificationExecutor<Ubigeo> {
}
