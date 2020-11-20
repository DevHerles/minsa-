package pe.gob.minsa.microservicio.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.gob.minsa.microservicio.model.Support;
import pe.gob.minsa.microservicio.search.SearchRequestDto;
import pe.gob.minsa.microservicio.service.SupportService;
import pe.gob.minsa.microservicio.utils.ApiResponseEntity;

import javax.validation.Valid;

@Api(tags = {"Support microservice"})
@CrossOrigin
@Controller
@AllArgsConstructor
@RequestMapping("/api/supports")
public class SupportController {
    private final SupportService supportService;

    @GetMapping
    public ResponseEntity<?> findAll(@Valid SearchRequestDto request) {
        return new ResponseEntity<>(supportService.findAll(request), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Support support) {
        try {
            support.setId(null);
            return ApiResponseEntity.successCreate(supportService.save(support));
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @RequestMapping(value = "{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            Support support = supportService.findById(id);
            return support != null ? ApiResponseEntity.successRead(support) : ApiResponseEntity.notFound(Support.class, id);
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Support support) {
        try {
            Support _support = supportService.findById(id);
            if(_support == null) return ApiResponseEntity.notFound(Support.class, id);
            BeanUtils.copyProperties(support, _support);
            return ApiResponseEntity.successUpdate(supportService.save(_support));
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            Support support = supportService.findById(id);
            if(support == null) return ApiResponseEntity.notFound(Support.class, id);
            supportService.delete(support);
            return ApiResponseEntity.successDelete();
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }
}
