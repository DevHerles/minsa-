package pe.gob.minsa.microservicio.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.gob.minsa.microservicio.model.Company;
import pe.gob.minsa.microservicio.model.Support;
import pe.gob.minsa.microservicio.model.Ubigeo;
import pe.gob.minsa.microservicio.search.SearchRequestDto;
import pe.gob.minsa.microservicio.service.CompanyService;
import pe.gob.minsa.microservicio.service.SupportService;
import pe.gob.minsa.microservicio.service.UbigeoService;
import pe.gob.minsa.microservicio.utils.ApiResponseEntity;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Api(tags = {"Company microservice"})
@CrossOrigin
@Controller
@AllArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final SupportService supportService;
    private final UbigeoService ubigeoService;

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

    @PutMapping(value = "{companyId}/ubigeo")
    public ResponseEntity<?> save(@Valid @RequestBody Ubigeo ubigeo, @PathVariable Long companyId) {
        try {

            Company company = companyService.findById(companyId);
            if (company == null) return ApiResponseEntity.error("La empresa no existe.");
            company.setUbigeo(ubigeo);
            return ApiResponseEntity.successCreate(companyService.save(company));
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @PostMapping(value = "{companyId}/support")
    public ResponseEntity<?> updateSupport(@PathVariable("companyId") Long companyId, @Valid @RequestBody Support support) {
        try {
            Company company = companyService.findById(companyId);
            if(company == null) return ApiResponseEntity.notFound(Company.class, companyId);
            support.setCompany(company);
            Support _support = supportService.save(support);
            return ApiResponseEntity.successUpdate(_support);
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @PostMapping(value = "{companyId}/ubigeo")
    public ResponseEntity<?> updateUbigeo(@PathVariable("companyId") Long companyId, @Valid @RequestBody Ubigeo ubigeo) {
        try {
            Company company = companyService.findById(companyId);
            if(company == null) return ApiResponseEntity.notFound(Company.class, companyId);
            company.setUbigeo(ubigeo);
            return ApiResponseEntity.successUpdate(companyService.save(company));
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

    @RequestMapping(value = "{id}/supports")
    public ResponseEntity<?> findCompanySupportsId(@PathVariable("id") Long id) {
        try {
            List<Support> supports = supportService.findByCompanyId(id);
            return supports != null ? ApiResponseEntity.successRead(supports) : ApiResponseEntity.notFound(Company.class, id);
        } catch (Exception exception) {
            return ApiResponseEntity.error(exception);
        }
    }

    @RequestMapping(value = "{companyId}/ubigeo")
    public ResponseEntity<?> findCompanyUbigeoId(@PathVariable("companyId") Long companyId) {
        try {
            Company company = companyService.findById(companyId);
            if(company.getUbigeo().getId() == null) ApiResponseEntity.notFound(Ubigeo.class, companyId);
            Ubigeo ubigeo = ubigeoService.findById(company.getUbigeo().getId());
            return ubigeo != null ? ApiResponseEntity.successRead(ubigeo) : ApiResponseEntity.notFound(Company.class, companyId);
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
