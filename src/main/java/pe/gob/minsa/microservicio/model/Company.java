
package pe.gob.minsa.microservicio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.gob.minsa.microservicio.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity @Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "companies")
public class Company extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String ruc;

    String owner;
    String phone;

    @Email
    String email;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Support> supports = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    Ubigeo ubigeo;
}
