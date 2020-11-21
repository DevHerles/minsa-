package pe.gob.minsa.microservicio.dto;

import lombok.Data;
import pe.gob.minsa.microservicio.model.Company;

@Data
public class SupportDto {
    public Long id;
    public String description;
    public double quantity;
    public int times;
    public double total;
    public Company company;
}
