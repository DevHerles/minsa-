package pe.gob.minsa.microservicio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.gob.minsa.microservicio.model.audit.DateAudit;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "supports")
public class Support extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String description;
    double quantity;
    int times;
    double total;

    @JsonIgnore
    @ManyToOne
    //@JoinColumn(name = "company_id", insertable = false, updatable = false)
    Company company;
}
