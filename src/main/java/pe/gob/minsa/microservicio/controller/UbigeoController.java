package pe.gob.minsa.microservicio.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.gob.minsa.microservicio.model.Ubigeo;
import pe.gob.minsa.microservicio.search.SearchRequestDto;
import pe.gob.minsa.microservicio.service.UbigeoService;
import pe.gob.minsa.microservicio.utils.ApiResponseEntity;

import javax.validation.Valid;

@Api(tags = {"Ubigeo microservice"})
@CrossOrigin
@Controller
@AllArgsConstructor
@RequestMapping("/api/ubigeo")
public class UbigeoController {
    private final UbigeoService ubigeoService;

    @GetMapping
    public ResponseEntity<?> findAll(@Valid SearchRequestDto request) {
        return new ResponseEntity<>(ubigeoService.findAll(request), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Ubigeo ubigeo) {
        try {
            ubigeo.setId(null);
            return ApiResponseEntity.successCreate(ubigeoService.save(ubigeo));
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @RequestMapping(value = "{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            Ubigeo ubigeo = ubigeoService.findById(id);
            return ubigeo != null ? ApiResponseEntity.successRead(ubigeo) : ApiResponseEntity.notFound(Ubigeo.class, id);
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Ubigeo ubigeo) {
        try {
            Ubigeo _ubigeo = ubigeoService.findById(id);
            if(_ubigeo == null) return ApiResponseEntity.notFound(Ubigeo.class, id);
            BeanUtils.copyProperties(ubigeo, _ubigeo);
            return ApiResponseEntity.successUpdate(ubigeoService.save(_ubigeo));
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            Ubigeo ubigeo = ubigeoService.findById(id);
            if(ubigeo == null) return ApiResponseEntity.notFound(Ubigeo.class, id);
            ubigeoService.delete(ubigeo);
            return ApiResponseEntity.successDelete();
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }
}
