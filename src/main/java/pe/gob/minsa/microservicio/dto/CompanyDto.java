package pe.gob.minsa.microservicio.dto;

import lombok.Data;

@Data
public class CompanyDto {
    public Long id;
    public String name;
    public String ruc;
    public String owner;
    public String phone;
    public String email;
}
