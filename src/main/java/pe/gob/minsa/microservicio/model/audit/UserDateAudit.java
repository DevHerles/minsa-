package pe.gob.minsa.microservicio.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(value = {"createdBy", "updatedBy"}, allowGetters = true)
@Data @AllArgsConstructor @NoArgsConstructor
public abstract class UserDateAudit extends DateAudit {
    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;
}
