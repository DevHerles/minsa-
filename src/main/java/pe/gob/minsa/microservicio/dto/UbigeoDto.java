package pe.gob.minsa.microservicio.dto;

import lombok.Data;

@Data
public class UbigeoDto {
    public Long id;
    public String department;
    public String province;
    public String district;
}
