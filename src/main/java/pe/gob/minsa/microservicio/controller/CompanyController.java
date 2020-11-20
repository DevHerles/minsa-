package pe.gob.minsa.microservicio.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.gob.minsa.microservicio.model.Company;
import pe.gob.minsa.microservicio.search.SearchRequestDto;
import pe.gob.minsa.microservicio.service.CompanyService;
import pe.gob.minsa.microservicio.utils.ApiResponseEntity;

import javax.validation.Valid;

@Api(tags = {"Company microservice"})
@CrossOrigin
@Controller
@AllArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> findAll(@Valid SearchRequestDto request) {
        return new ResponseEntity<>(companyService.findAll(request), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Company company) {
        try {
            company.setId(null);
            return ApiResponseEntity.successCreate(companyService.save(company));
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @RequestMapping(value = "{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            Company company = companyService.findById(id);
            return company != null ? ApiResponseEntity.successRead(company) : ApiResponseEntity.notFound(Company.class, id);
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Company company) {
        try {
            Company _company = companyService.findById(id);
            if(_company == null) return ApiResponseEntity.notFound(Company.class, id);
            BeanUtils.copyProperties(company, _company);
            return ApiResponseEntity.successUpdate(companyService.save(_company));
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            Company company = companyService.findById(id);
            if(company == null) return ApiResponseEntity.notFound(Company.class, id);
            companyService.delete(company);
            return ApiResponseEntity.successDelete();
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }
}
